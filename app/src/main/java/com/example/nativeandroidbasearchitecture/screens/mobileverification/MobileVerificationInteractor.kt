package com.example.nativeandroidbasearchitecture.screens.mobileverification

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MobileVerificationInteractor(private val api: MobileVerificationApi) {
    fun requestOtp(mobileNumber: String): Flow<Boolean> = flow {
        emit(api.requestOtp(mobileNumber))
    }

    fun verifyOtp(mobileNumber: String, otp: String): Flow<Boolean> = flow {
        emit(api.verifyOtp(mobileNumber, otp))
    }
}
