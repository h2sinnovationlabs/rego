package com.example.nativeandroidbasearchitecture.screens.notifications

import com.example.nativeandroidbasearchitecture.screens.base.DataState
import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import com.example.nativeandroidbasearchitecture.screens.base.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotificationInteractor(private val api: NotificationApi) {
    fun getNotifications(): Flow<DataState<List<NotificationItem>>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val notifications = api.getNotifications()
            emit(DataState.Data(notifications))
        } catch (e: Exception) {
            emit(
                DataState.Error(
                    UIComponent.ErrorData(
                        title = "Failed",
                        message = e.message ?: "Unknown Error",
                        buttonText = "Retry"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}
