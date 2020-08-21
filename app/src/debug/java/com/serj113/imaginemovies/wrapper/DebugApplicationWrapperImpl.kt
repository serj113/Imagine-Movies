package com.serj113.imaginemovies.wrapper

import android.app.Application

class DebugApplicationWrapperImpl : ApplicationWrapper {
    override fun setupFlipper(application: Application) {
        FlipperDeps.setup(application)
    }
}