package com.example.nativeandroidbasearchitecture.screens.main.profile

import com.example.nativeandroidbasearchitecture.CommonResponse

data class ProfileData(
    val name: String,
    val phone: String,
    val email: String,
    val customerId: String
)

interface ProfileApi {
    fun getProfile(): CommonResponse<ProfileData>
}