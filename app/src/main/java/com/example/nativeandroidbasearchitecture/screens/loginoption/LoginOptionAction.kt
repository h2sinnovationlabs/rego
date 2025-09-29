package com.example.nativeandroidbasearchitecture.screens.loginoption

import com.example.nativeandroidbasearchitecture.screens.base.ViewSingleAction

sealed class LoginOptionAction : ViewSingleAction {
    object OnLoginClick : LoginOptionAction()
    object OnSignUpClick : LoginOptionAction()
}