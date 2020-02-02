package org.queiroz.themoviedb.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import org.queiroz.themoviedb.TheMovieDbApp
import javax.inject.Singleton

@Module(
    includes = [
        ViewModelModule::class
    ]
)
internal class AppModule {

    @Provides
    fun provideContext(application: TheMovieDbApp): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
         context.getSharedPreferences("default", Context.MODE_PRIVATE)

}
