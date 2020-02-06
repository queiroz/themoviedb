package org.queiroz.themoviedb.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.queiroz.themoviedb.di.FragmentKey
import org.queiroz.themoviedb.di.factory.AppFragmentFactory
import org.queiroz.themoviedb.ui.movies.PopularMoviesFragment

@Module
abstract class FragmentsBindingModule {

    @Binds
    abstract fun fragmentFactory(factory: AppFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(PopularMoviesFragment::class)
    abstract fun popularMoviesFragment(fragment: PopularMoviesFragment): Fragment

}
