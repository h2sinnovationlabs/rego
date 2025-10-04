package com.example.nativeandroidbasearchitecture.screens.raiserequest

interface RaiseRequestApi {
    suspend fun getFormOptions(): RaiseRequestFormOptions
    suspend fun submitRequest(data: RaiseRequestFormData): Boolean
}
