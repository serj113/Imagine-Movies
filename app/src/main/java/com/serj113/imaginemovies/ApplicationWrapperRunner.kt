package com.serj113.imaginemovies

import android.app.Application
import com.serj113.imaginemovies.wrapper.ApplicationWrapper
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent

object ApplicationWrapperRunner {

    @InstallIn(ApplicationComponent::class)
    @EntryPoint
    interface ApplicationWrapperRunnerEntryPoint {
        fun applicationWrapper(): ApplicationWrapper
    }

    fun runWrapper(application: Application) {
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            application,
            ApplicationWrapperRunnerEntryPoint::class.java
        )
        val applicationWrapper = hiltEntryPoint.applicationWrapper()
        applicationWrapper.setupFlipper(application)
    }
}