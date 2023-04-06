package com.everfittest.android

import android.app.Application
import com.everfittest.android.util.LineNumberDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class EverfitTestApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        setupLogging()
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(LineNumberDebugTree(BuildConfig.FLAVOR))
        }
    }
}
