package org.queiroz.themoviedb.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.queiroz.themoviedb.AppNavHostFragment

@Module
abstract class AppNavHostFragmentModule {

    @ContributesAndroidInjector(modules = [FragmentsBindingModule::class])
    abstract fun appNavHostFragment(): AppNavHostFragment

}
