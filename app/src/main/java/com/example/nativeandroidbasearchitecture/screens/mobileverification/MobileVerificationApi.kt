package com.example.nativeandroidbasearchitecture.screens.mobileverification

interface MobileVerificationApi {
    suspend fun requestOtp(mobileNumber: String): Boolean
    suspend fun verifyOtp(mobileNumber: String, otp: String): Boolean
}
