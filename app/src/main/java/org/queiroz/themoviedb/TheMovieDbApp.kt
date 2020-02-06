package org.queiroz.themoviedb

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import org.queiroz.themoviedb.di.AppComponent
import org.queiroz.themoviedb.di.DaggerAppComponent
import timber.log.Timber

class TheMovieDbApp : DaggerApplication() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.uprootAll()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

}
