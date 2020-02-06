package org.queiroz.themoviedb.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import org.queiroz.themoviedb.TheMovieDbApp
import org.queiroz.themoviedb.di.module.ActivityBindingModule
import org.queiroz.themoviedb.di.module.AppModule
import org.queiroz.themoviedb.di.module.NetModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (AppModule::class),
        (NetModule::class),
        (ActivityBindingModule::class)
    ]
)
interface AppComponent : AndroidInjector<TheMovieDbApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: TheMovieDbApp): AppComponent
    }

}
