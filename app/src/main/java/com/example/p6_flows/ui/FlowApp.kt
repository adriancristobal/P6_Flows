package com.example.p6_flows.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

//anotacion necesaria para que empiece ha hacer codigo por debajo (preguntar a oscar)
@HiltAndroidApp
class FlowApp : Application() {
    //timber
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}