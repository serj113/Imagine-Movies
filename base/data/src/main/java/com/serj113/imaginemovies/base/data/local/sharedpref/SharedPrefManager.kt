package com.serj113.imaginemovies.base.data.local.sharedpref

interface SharedPrefManager {

    fun saveText(key: String, text: String)

    fun getText(key: String): String

    companion object {
        const val SHARED_PREF_NAME = "IMAGINE_MOVIES_SHARED_PREFERENCES"
    }
}