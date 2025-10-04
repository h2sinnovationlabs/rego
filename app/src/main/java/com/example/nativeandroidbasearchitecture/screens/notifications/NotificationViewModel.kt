package com.example.nativeandroidbasearchitecture.screens.notifications

import androidx.lifecycle.viewModelScope
import com.example.nativeandroidbasearchitecture.screens.base.BaseViewModel
import com.example.nativeandroidbasearchitecture.screens.base.DataState
import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NotificationViewModel(private val interactor: NotificationInteractor) :
    BaseViewModel<NotificationEvent, NotificationViewState, NotificationAction>() {

    override fun setInitialState() = NotificationViewState()

    override fun onTriggerEvent(event: NotificationEvent) {
        when (event) {
            is NotificationEvent.Init -> {
                getNotifications()
            }

            is NotificationEvent.NotificationClicked -> {
                // handle item click if needed
            }
        }
    }

    private fun getNotifications() {
        viewModelScope.launch {
            interactor.getNotifications().collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        setState { copy(progressBarState = dataState.progressBarState) }
                    }

                    is DataState.Data -> {
                        setState {
                            copy(
                                notifications = dataState.data,
                                progressBarState = ProgressBarState.Idle
                            )
                        }
                    }

                    is DataState.Error -> {
                        setState { copy(progressBarState = ProgressBarState.Idle) }
                        setError { dataState.uiComponent }
                    }

                    else -> {}
                }
            }
        }
    }
}
