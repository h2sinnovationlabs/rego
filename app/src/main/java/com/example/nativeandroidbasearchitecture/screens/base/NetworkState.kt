package com.example.nativeandroidbasearchitecture.screens.base

sealed class NetworkState{

   data object Good: NetworkState()

   data object Failed: NetworkState()

}
