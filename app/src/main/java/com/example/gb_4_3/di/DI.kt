package com.example.gb_4_3.di

import com.example.gb_4_3.data.repository.MapsRepository
import com.example.gb_4_3.data.repository.MapsRepositoryImpl
import com.example.gb_4_3.presentation.viewmodel.MapsViewModel
import com.example.gb_4_3.presentation.viewmodel.MarkerDetailsViewModel
import com.example.gb_4_3.presentation.viewmodel.MarkersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DI {
    fun getModule() = module {
        single<MapsRepository> { MapsRepositoryImpl() }

        viewModel { MapsViewModel(mapsRepository = get()) }

        viewModel { MarkersListViewModel(mapsRepository = get()) }

        viewModel { MarkerDetailsViewModel(mapsRepository = get()) }
    }
}
