package com.example.nativeandroidbasearchitecture.di

import com.example.nativeandroidbasearchitecture.screens.loginoption.LoginOptionViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginOptionViewModel() }
}