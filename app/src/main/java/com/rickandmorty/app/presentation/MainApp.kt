package com.rickandmorty.app.presentation

import android.app.Application
import com.rickandmorty.app.dependencyinjection.dataModule
import com.rickandmorty.app.dependencyinjection.domainModule
import com.rickandmorty.app.dependencyinjection.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApp)
            modules(
                presentationModule,
                dataModule,
                domainModule,
            )
        }
    }
}
