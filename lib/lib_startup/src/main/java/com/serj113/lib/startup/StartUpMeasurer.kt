package com.serj113.lib.startup

import android.util.Log

object StartUpMeasurer {
    var startTime = 0L
    var delta = 0L

    fun start() {
        startTime = System.currentTimeMillis()
    }

    fun stop() {
        delta = System.currentTimeMillis() - startTime
        Log.d("StartUpMeasurer", delta.toString())
    }
}
