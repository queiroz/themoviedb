package org.queiroz.themoviedb.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.queiroz.themoviedb.MainActivity
import org.queiroz.themoviedb.di.scopes.ActivityScoped
import org.queiroz.themoviedb.ui.main.MainModule

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            (MainModule::class)
        ]
    )
    internal abstract fun mainActivity(): MainActivity

}
