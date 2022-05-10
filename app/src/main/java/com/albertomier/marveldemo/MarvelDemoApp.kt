package com.albertomier.marveldemo

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelDemoApp : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: MarvelDemoApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = MarvelDemoApp.applicationContext()
    }
}