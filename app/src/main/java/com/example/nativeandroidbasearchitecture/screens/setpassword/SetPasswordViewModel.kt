package com.example.nativeandroidbasearchitecture.screens.setpassword

import androidx.lifecycle.viewModelScope
import com.example.nativeandroidbasearchitecture.screens.base.BaseViewModel
import com.example.nativeandroidbasearchitecture.screens.base.DataState
import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SetPasswordViewModel(private val interactor: SetPasswordInteractor) :
    BaseViewModel<SetPasswordEvent, SetPasswordViewState, SetPasswordAction>() {

    override fun setInitialState() = SetPasswordViewState()

    override fun onTriggerEvent(event: SetPasswordEvent) {
        when (event) {
            is SetPasswordEvent.Init -> setState { copy() }
            is SetPasswordEvent.PasswordChanged -> setState { copy(password = event.password) }
            is SetPasswordEvent.ConfirmPasswordChanged -> setState { copy(confirmPassword = event.password) }
            is SetPasswordEvent.SetPassword -> setPassword(event.userId, event.password)
        }
    }

    private fun setPassword(userId: String, password: String) {
        viewModelScope.launch {
            interactor.setPassword(userId, password).collect { dataState ->
                when (dataState) {
                    is DataState.Data -> setState {
                        copy(
                            isPasswordSet = dataState.data,
                            progressBarState = ProgressBarState.Idle
                        )
                    }

                    is DataState.Error -> setState {
                        copy(
                            error = dataState.uiComponent.toString(),
                            progressBarState = ProgressBarState.Idle
                        )
                    }

                    is DataState.Loading -> setState { copy(progressBarState = dataState.progressBarState) }
                    else -> {}
                }
            }
        }
    }
}
