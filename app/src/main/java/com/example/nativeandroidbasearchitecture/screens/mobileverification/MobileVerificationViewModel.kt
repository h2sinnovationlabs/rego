package com.example.nativeandroidbasearchitecture.screens.mobileverification

import androidx.lifecycle.viewModelScope
import com.example.nativeandroidbasearchitecture.screens.base.BaseViewModel
import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MobileVerificationViewModel(
    private val interactor: MobileVerificationInteractor
) : BaseViewModel<MobileVerificationEvent, MobileVerificationViewState, MobileVerificationAction>() {

    override fun setInitialState() = MobileVerificationViewState()

    override fun onTriggerEvent(event: MobileVerificationEvent) {
        when (event) {
            is MobileVerificationEvent.Init -> {
                setState { copy(progressBarState = ProgressBarState.Idle) }
            }

            is MobileVerificationEvent.MobileNumberChanged -> {
                setState {
                    val isValid = event.value.length == 10 && event.value.all { it.isDigit() }
                    copy(mobileNumber = event.value, isValidMobile = isValid)
                }
            }

            is MobileVerificationEvent.GetOtp -> {
                viewModelScope.launch {
                    setState { copy(progressBarState = ProgressBarState.Loading) }
                    interactor.requestOtp(state.value.mobileNumber).collect { result ->
                        setState {
                            copy(
                                isOtpRequested = result,
                                progressBarState = ProgressBarState.Idle
                            )
                        }
                    }
                    startResendTimer()
                }
            }

            is MobileVerificationEvent.OtpChanged -> {
                setState { copy(otp = event.value) }
            }

            is MobileVerificationEvent.VerifyOtp -> {
                viewModelScope.launch {
                    setState { copy(progressBarState = ProgressBarState.Loading) }
                    interactor.verifyOtp(state.value.mobileNumber, state.value.otp)
                        .collect { result ->
                            setState {
                                copy(
                                    isOtpVerified = result,
                                    progressBarState = ProgressBarState.Idle
                                )
                            }
                        }
                }
            }

            is MobileVerificationEvent.ResendOtp -> {
                setState { copy(resendTimer = 20, isResendEnabled = false) }
                viewModelScope.launch { startResendTimer() }
            }
        }
    }

    private fun startResendTimer() {
        viewModelScope.launch {
            setState { copy(resendTimer = 20, isResendEnabled = false) }
            while (state.value.resendTimer > 0) {
                delay(1000)
                setState { copy(resendTimer = state.value.resendTimer - 1) }
            }
            setState { copy(isResendEnabled = true) }
        }
    }
}
