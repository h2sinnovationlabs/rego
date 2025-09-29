package com.example.nativeandroidbasearchitecture.screens.loginoption

import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import com.example.nativeandroidbasearchitecture.screens.base.ViewState

data class LoginOptionViewState(val progressBarState: ProgressBarState = ProgressBarState.Idle) :
    ViewState