package com.example.nativeandroidbasearchitecture.screens.base

sealed class ProgressBarState {

    data object Loading : ProgressBarState()

    data object Idle : ProgressBarState()
    data object Refreshing : ProgressBarState()

}

