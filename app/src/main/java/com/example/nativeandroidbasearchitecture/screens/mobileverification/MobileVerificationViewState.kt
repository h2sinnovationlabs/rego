package com.example.nativeandroidbasearchitecture.screens.mobileverification

import androidx.compose.runtime.Immutable
import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import com.example.nativeandroidbasearchitecture.screens.base.ViewState

@Immutable
data class MobileVerificationViewState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val mobileNumber: String = "",
    val otp: String = "",
    val resendTimer: Int = 20,
    val isResendEnabled: Boolean = false,
    val isOtpRequested: Boolean = false,
    val isOtpVerified: Boolean = false,
    val isValidMobile: Boolean = false
) : ViewState
