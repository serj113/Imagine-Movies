package com.serj113.data.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.serj113.data.local.sharedpref.SharedPrefManager
import com.serj113.data.local.sharedpref.SharedPrefManager.Companion.SHARED_PREF_NAME
import com.serj113.data.local.sharedpref.SharedPrefManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SharedPrefModule {
    @Provides
    @Singleton
    internal fun provideSharedPrefManager(
        @ApplicationContext appContext: Context
    ): SharedPrefManager {
        val sharedPreferences = appContext.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        return SharedPrefManagerImpl(sharedPreferences)
    }
}
