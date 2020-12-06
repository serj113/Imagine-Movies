package com.serj113.data.local.sharedpref

import android.content.SharedPreferences

interface SharedPrefManager {

    fun saveText(key: String, text: String)

    fun getText(key: String): String

    companion object {
        const val SHARED_PREF_NAME = "IMAGINE_MOVIES_SHARED_PREFERENCES"
    }
}