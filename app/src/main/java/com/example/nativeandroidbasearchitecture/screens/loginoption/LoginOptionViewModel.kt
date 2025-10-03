package com.example.nativeandroidbasearchitecture.screens.loginoption

import com.example.nativeandroidbasearchitecture.screens.base.BaseViewModel

class LoginOptionViewModel :
    BaseViewModel<LoginOptionEvent, LoginOptionViewState, LoginOptionAction>() {

    override fun setInitialState() = LoginOptionViewState()

    override fun onTriggerEvent(event: LoginOptionEvent) {
        when (event) {
            is LoginOptionEvent.Init -> {
                init()
            }
        }
    }

    private fun init() {

    }
}