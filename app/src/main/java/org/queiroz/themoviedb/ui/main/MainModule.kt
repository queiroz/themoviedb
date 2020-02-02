package org.queiroz.themoviedb.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.queiroz.themoviedb.di.scopes.FragmentScoped

@Module
internal abstract class MainModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeMainFragment(): MainFragment

}
