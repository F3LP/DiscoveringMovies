package com.facd.discoveringmovies

import com.facd.discoveringmovies.data.api.networkModule
import com.facd.discoveringmovies.domain.di.repositoryModule
import com.facd.discoveringmovies.domain.di.useCaseModule
import com.facd.discoveringmovies.presentation.viewmodel.viewModelDetailModule
import com.facd.discoveringmovies.presentation.viewmodel.viewModelListMovieModule
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito


class CheckModulesTest: KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    // Verify the Koin configuration
    @Test
    fun checkAllModules() = checkModules {
        modules(
            viewModelDetailModule,
            viewModelListMovieModule,
            repositoryModule,
            networkModule,
            useCaseModule
            )
    }
}
