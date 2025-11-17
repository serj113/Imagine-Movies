package com.serj113.imaginemovies.base.data.local.sharedpref

import android.content.SharedPreferences

class SharedPrefManagerImpl(
    private var sharedPref: SharedPreferences
) : SharedPrefManager {

    private var editor = sharedPref.edit()

    override fun saveText(key: String, text: String) {
        editor.putString(key, text)
        editor.apply()
    }

    override fun getText(key: String) = sharedPref.getString(key, "") ?: ""

}