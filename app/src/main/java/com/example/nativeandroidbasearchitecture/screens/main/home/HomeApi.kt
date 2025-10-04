package com.example.nativeandroidbasearchitecture.screens.main.home

import com.example.nativeandroidbasearchitecture.CommonResponse

interface HomeApi {
    fun getHomeData(): CommonResponse<String> //Replace this by actual response class
}