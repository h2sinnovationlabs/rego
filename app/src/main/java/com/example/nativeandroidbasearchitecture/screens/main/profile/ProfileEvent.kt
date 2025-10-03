package com.example.nativeandroidbasearchitecture.screens.main.profile

import com.example.nativeandroidbasearchitecture.screens.base.ViewEvent

sealed class ProfileEvent : ViewEvent {
    object Init : ProfileEvent()
}