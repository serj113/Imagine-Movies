package com.serj113.imaginemovies.wrapper

object FlipperDeps {

//    @InstallIn(SingletonComponent::class)
//    @EntryPoint
//    interface FlipperDepsEntryPoint {
//        fun networkFlipperPlugin(): NetworkFlipperPlugin
//    }
//
//    fun setup(application: Application) {
//        if (!BuildConfig.DEBUG || !FlipperUtils.shouldEnableFlipper(application)) return
//
//        SoLoader.init(application, false)
//        val networkFlipperPlugin = getNetworkFlipperPlugin(application)
//        val sharedPrefPlugin = SharedPreferencesFlipperPlugin(application, SHARED_PREF_NAME)
//        val flipperClient = AndroidFlipperClient.getInstance(application)
//        flipperClient.addPlugin(networkFlipperPlugin)
//        flipperClient.addPlugin(sharedPrefPlugin)
//        flipperClient.start()
//    }
//
//    private fun getNetworkFlipperPlugin(application: Application): NetworkFlipperPlugin {
//        val hiltEntryPoint = EntryPointAccessors.fromApplication(
//            application,
//            FlipperDepsEntryPoint::class.java
//        )
//        return hiltEntryPoint.networkFlipperPlugin()
//    }
}
