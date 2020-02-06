package org.queiroz.themoviedb.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.queiroz.themoviedb.di.ViewModelKey
import org.queiroz.themoviedb.di.factory.AppViewModelFactory
import org.queiroz.themoviedb.movies.MoviesViewModel

@Module
abstract class ViewModelBindingModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel

}
