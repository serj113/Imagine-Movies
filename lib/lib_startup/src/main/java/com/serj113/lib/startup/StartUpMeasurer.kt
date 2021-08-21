package com.serj113.lib.startup

import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import com.google.firebase.perf.metrics.Trace

object StartUpMeasurer {
    private const val TRACE_NAME = "cold_start"
    private var trace: Trace? = null

    fun start() {
        trace = Firebase.performance.newTrace(TRACE_NAME)
        trace?.start()
    }

    fun stop() {
        trace?.stop()
        trace = null
    }
}
