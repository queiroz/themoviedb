package org.queiroz.themoviedb.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.queiroz.themoviedb.MainActivity
import org.queiroz.themoviedb.di.scopes.ActivityScoped

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [AppNavHostFragmentModule::class])
    internal abstract fun mainActivity(): MainActivity

}
