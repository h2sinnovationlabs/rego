package com.example.nativeandroidbasearchitecture.screens.setpassword

interface SetPasswordApi {
    suspend fun setPassword(userId: String, password: String): Boolean
}
