package com.example.nativeandroidbasearchitecture.screens.main.home

import com.example.nativeandroidbasearchitecture.CommonResponse

class HomeApiImpl(
    /** ktor http client */
) : HomeApi {
    override fun getHomeData(): CommonResponse<String> {
        return CommonResponse(data = "home data", status = true, message = "success")
    }
}