package org.queiroz.themoviedb.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.queiroz.themoviedb.di.scopes.FragmentScoped
import org.queiroz.themoviedb.ui.movies.PopularMoviesFragment

@Module
internal abstract class MainModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributePopularMoviesFragment(): PopularMoviesFragment

}
