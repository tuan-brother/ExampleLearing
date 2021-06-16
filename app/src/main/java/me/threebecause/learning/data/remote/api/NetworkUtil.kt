package me.threebecause.learning.data.remote.api

import android.content.Context
import android.net.ConnectivityManager

open class NetworkUtil {
    companion object {
        const val FOOTBALL_API_URL = "https://api.football-data.org/v2/"
        const val CREST_API_URL = "https://football-crest-api.herokuapp.com/crest/"
        const val COVER_IMAGE_URL = "https://loremflickr.com/320/240/"
        const val REDDIT_API_URL = "https://www.reddit.com/"

        const val IMAGE_QUALITY_SD = "sd"
        const val IMAGE_QUALITY_HD = "hd"
    }

    fun hasNetwork(context: Context): Boolean {
        var isConnected = false // Initial Value
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected) {
            isConnected = true
        }
        return isConnected
    }

    fun getCrestUrl(teamId: Int, quality: String): String {
        return "$CREST_API_URL$quality/$teamId"
    }
}