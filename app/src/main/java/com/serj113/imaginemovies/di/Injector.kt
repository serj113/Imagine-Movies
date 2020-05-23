package com.serj113.imaginemovies.di

import android.app.Application
import com.serj113.imaginemovies.App
import com.serj113.imaginemovies.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidInjectionModule::class),
    (FragmentModule::class),
    (UseCaseModule::class),
    (RepositoryModule::class),
    (ViewModelModule::class),
    (ApiModule::class)
])
interface Injector : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): Injector
    }

    override fun inject(app: App)
}