package com.example.nativeandroidbasearchitecture.di

import com.example.nativeandroidbasearchitecture.screens.loginoption.LoginOptionViewModel
import com.example.nativeandroidbasearchitecture.screens.main.home.HomeApi
import com.example.nativeandroidbasearchitecture.screens.main.home.HomeApiImpl
import com.example.nativeandroidbasearchitecture.screens.main.home.HomeInteractor
import com.example.nativeandroidbasearchitecture.screens.main.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginOptionViewModel() }

    factory<HomeApi> { HomeApiImpl() }
    factory { HomeInteractor(get()) }
    viewModel { HomeViewModel(get()) }

}