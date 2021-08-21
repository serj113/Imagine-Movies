package com.serj113.imaginemovies

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Process
import android.os.SystemClock
import android.util.Log
import com.serj113.imaginemovies.NextDrawListener.Companion.onNextDraw
import com.serj113.imaginemovies.WindowDelegateCallback.Companion.onDecorViewReady
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        registerActivityCallback()
        ApplicationWrapperRunner.runWrapper(this)
    }

    fun registerActivityCallback() {
        var firstDraw = false
        val handler = Handler()

        registerActivityLifecycleCallbacks(
            object : ActivityLifecycleCallbacks {
                override fun onActivityCreated(
                    activity: Activity,
                    savedInstanceState: Bundle?
                ) {
                    if (firstDraw) return
                    val name = activity::class.java.simpleName
                    val window = activity.window
                    window.onDecorViewReady {
                        window.decorView.onNextDraw {
                            if (firstDraw) return@onNextDraw
                            firstDraw = true
                            handler.postAtFrontOfQueue {
                                val start = Process.getStartUptimeMillis()
                                val now = SystemClock.uptimeMillis()
                                val startDurationMs = now - start
                                Log.d(
                                    "AppStart",
                                    "Displayed start in $start ms"
                                )
                                Log.d(
                                    "AppStart",
                                    "Displayed now in $now ms"
                                )
                                Log.d(
                                    "AppStart",
                                    "Displayed $name in $startDurationMs ms"
                                )
                            }
                        }
                    }
                }

                override fun onActivityStarted(p0: Activity) = Unit

                override fun onActivityResumed(p0: Activity) = Unit

                override fun onActivityPaused(p0: Activity) = Unit

                override fun onActivityStopped(p0: Activity) = Unit

                override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) = Unit

                override fun onActivityDestroyed(p0: Activity) = Unit
            })
    }
}
