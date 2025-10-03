package com.example.nativeandroidbasearchitecture.di

import com.example.nativeandroidbasearchitecture.screens.loginoption.LoginOptionViewModel
import com.example.nativeandroidbasearchitecture.screens.main.home.HomeApi
import com.example.nativeandroidbasearchitecture.screens.main.home.HomeApiImpl
import com.example.nativeandroidbasearchitecture.screens.main.home.HomeInteractor
import com.example.nativeandroidbasearchitecture.screens.main.home.HomeViewModel
import com.example.nativeandroidbasearchitecture.screens.joinus.JoinUsApi
import com.example.nativeandroidbasearchitecture.screens.joinus.JoinUsApiImpl
import com.example.nativeandroidbasearchitecture.screens.joinus.JoinUsViewModel
import com.example.nativeandroidbasearchitecture.screens.notifications.NotificationApi
import com.example.nativeandroidbasearchitecture.screens.notifications.NotificationApiImpl
import com.example.nativeandroidbasearchitecture.screens.notifications.NotificationInteractor
import com.example.nativeandroidbasearchitecture.screens.notifications.NotificationViewModel
import com.example.nativeandroidbasearchitecture.screens.orderdetails.OrderDetailsApi
import com.example.nativeandroidbasearchitecture.screens.orderdetails.OrderDetailsApiImpl
import com.example.nativeandroidbasearchitecture.screens.orderdetails.OrderDetailsInteractor
import com.example.nativeandroidbasearchitecture.screens.orderdetails.OrderDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginOptionViewModel() }

    factory<HomeApi> { HomeApiImpl() }
    factory { HomeInteractor(get()) }
    viewModel { HomeViewModel(get()) }

    // Join Us
    factory<JoinUsApi> { JoinUsApiImpl() }
    viewModel { JoinUsViewModel(get()) }

    // Notifications
    factory<NotificationApi> { NotificationApiImpl() }
    factory { NotificationInteractor(get()) }
    viewModel { NotificationViewModel(get()) }

    // Order Details
    factory<OrderDetailsApi> { OrderDetailsApiImpl() }
    factory { OrderDetailsInteractor(get()) }
    viewModel { OrderDetailsViewModel(get()) }
}