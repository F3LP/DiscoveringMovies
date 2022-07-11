package com.facd.discoveringmovies

import android.app.Application
import com.facd.discoveringmovies.data.api.networkModule
import com.facd.discoveringmovies.domain.di.repositoryModule
import com.facd.discoveringmovies.domain.di.useCaseModule
import com.facd.discoveringmovies.listmovies.viewModelListMovieModule
import com.facd.discoveringmovies.moviedetails.viewModelDetailModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class DiscoveringMoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@DiscoveringMoviesApplication)
            modules(listOf(repositoryModule, networkModule, useCaseModule, viewModelListMovieModule,
                viewModelDetailModule
            ))
        }

    }
}