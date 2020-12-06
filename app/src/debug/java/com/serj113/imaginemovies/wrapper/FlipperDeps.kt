package com.serj113.imaginemovies.wrapper

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.serj113.data.local.sharedpref.SharedPrefManager.Companion.SHARED_PREF_NAME
import com.serj113.imaginemovies.BuildConfig
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent

object FlipperDeps {

    @InstallIn(ApplicationComponent::class)
    @EntryPoint
    interface FlipperDepsEntryPoint {
        fun networkFlipperPlugin(): NetworkFlipperPlugin
    }

    fun setup(application: Application) {
        if (!BuildConfig.DEBUG || !FlipperUtils.shouldEnableFlipper(application)) return
        SoLoader.init(application, false)
        val networkFlipperPlugin = getNetworkFlipperPlugin(application)
        val sharedPrefPlugin = SharedPreferencesFlipperPlugin(application, SHARED_PREF_NAME)
        val flipperClient = AndroidFlipperClient.getInstance(application)
        flipperClient.addPlugin(networkFlipperPlugin)
        flipperClient.addPlugin(sharedPrefPlugin)
        flipperClient.start()
    }

    private fun getNetworkFlipperPlugin(application: Application): NetworkFlipperPlugin {
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            application,
            FlipperDepsEntryPoint::class.java
        )
        return hiltEntryPoint.networkFlipperPlugin()
    }
}