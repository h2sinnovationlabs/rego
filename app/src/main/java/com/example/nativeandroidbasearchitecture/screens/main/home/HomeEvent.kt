package com.example.nativeandroidbasearchitecture.screens.main.home

import com.example.nativeandroidbasearchitecture.screens.base.ViewEvent

sealed class HomeEvent : ViewEvent {
    object Init : HomeEvent()
}