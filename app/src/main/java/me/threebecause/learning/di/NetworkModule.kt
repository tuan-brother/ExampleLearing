package me.threebecause.learning.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.threebecause.learning.data.remote.api.ApiPath
import me.threebecause.learning.data.remote.api.ApiService
import me.threebecause.learning.enableLogging
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    val TIMEOUT = 10L

    @Singleton
    @Provides
    @Named("header")
    fun provideHeaderInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
//            val newUrl = request.url.newBuilder()
//                .addQueryParameter("api_key","1c121ebc643743d8b43f5d7ed1229867")
//                .build()
            val newRequest = request.newBuilder()
                .addHeader("X-Auth-Token","1c121ebc643743d8b43f5d7ed1229867")
                .method(request.method, request.body)
                .build()
            chain.proceed(newRequest)
        }

    @Provides
    fun provideOkHttpCache(context: Context): Cache =
        Cache(context.cacheDir, (10 * 1024 * 1024).toLong())

    @Singleton
    @Provides
    @Named("logging")
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = if (enableLogging()) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

//    @Singleton
//    @Provides
//    @Named("mock")
//    fun provideMockInterceptor(assetManager: AssetManager): MockInterceptor =
//        MockInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @Named("header") header: Interceptor
//        @Named("mock") mockInterceptor: MockInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(header)
            .apply {
                //            .addNetworkInterceptor(StethoInterceptor())
                if (enableLogging()) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(loggingInterceptor)
                }
//                if (BuildConfig.DEBUG && BuildConfig.MOCK_DATA) addInterceptor(mockInterceptor)
            }
            .build()

    @Singleton
    @Provides
    @Named("api")
    fun provideApiRedditRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiPath.REDDIT_API_URL)
            .client(okHttpClient).build()

    @Singleton
    @Provides
    @Named("apiFootball")
    fun provideAppRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.football-data.org/v2/")
            .client(okHttpClient)
            .build()
    @Singleton
    @Provides
    fun provideApiFootballService(
        @Named("apiFootball") retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

//    @Singleton
//    @Provides
//    @Named("api")
//    fun provideApiRedditService(
//        @Named("api") retrofit: Retrofit
//    ): ApiService = retrofit.create(ApiService::class.java)
}