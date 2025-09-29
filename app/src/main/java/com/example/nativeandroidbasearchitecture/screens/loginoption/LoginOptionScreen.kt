package com.example.nativeandroidbasearchitecture.screens.loginoption

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.nativeandroidbasearchitecture.screens.base.DefaultScreenUI
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginOptionScreen(onLogin: () -> Unit, onSignUp: () -> Unit) {
    val homeViewModel: LoginOptionViewModel = koinViewModel()
    val errors = homeViewModel.errors
    val state = homeViewModel.state.collectAsState()
    val events = homeViewModel::onTriggerEvent
    LaunchedEffect(key1 = Unit) {
        events(LoginOptionEvent.Init)
    }
    DefaultScreenUI(progressBarState = state.value.progressBarState) {

    }
}