package me.threebecause.learning

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

}
fun enableLogging() = BuildConfig.BUILD_TYPE != "release"