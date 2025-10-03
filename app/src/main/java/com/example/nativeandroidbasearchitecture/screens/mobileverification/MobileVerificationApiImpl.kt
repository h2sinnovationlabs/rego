package com.example.nativeandroidbasearchitecture.screens.mobileverification

class MobileVerificationApiImpl : MobileVerificationApi {
    override suspend fun requestOtp(mobileNumber: String): Boolean {
        // Simulate network call
        return true // success for stub
    }

    override suspend fun verifyOtp(mobileNumber: String, otp: String): Boolean {
        // Simulate network call & always allow 6-digit OTP to succeed
        return otp.length == 6
    }
}
