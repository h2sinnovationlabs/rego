package com.example.nativeandroidbasearchitecture.screens.setpassword

import androidx.compose.runtime.Immutable
import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import com.example.nativeandroidbasearchitecture.screens.base.ViewState

@Immutable
data class SetPasswordViewState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordSet: Boolean? = null,
    val error: String? = null
) : ViewState
