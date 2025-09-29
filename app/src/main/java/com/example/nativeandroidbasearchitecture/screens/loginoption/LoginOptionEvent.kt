package com.example.nativeandroidbasearchitecture.screens.loginoption

import com.example.nativeandroidbasearchitecture.screens.base.ViewEvent

sealed class LoginOptionEvent : ViewEvent {
    object Init : LoginOptionEvent()
}