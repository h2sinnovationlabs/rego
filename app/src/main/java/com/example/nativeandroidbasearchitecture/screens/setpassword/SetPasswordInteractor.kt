package com.example.nativeandroidbasearchitecture.screens.setpassword

import com.example.nativeandroidbasearchitecture.screens.base.DataState
import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import com.example.nativeandroidbasearchitecture.screens.base.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SetPasswordInteractor(private val api: SetPasswordApi) {
    fun setPassword(userId: String, password: String): Flow<DataState<Boolean>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val result = api.setPassword(userId, password)
            emit(DataState.Data(result))
        } catch (e: Exception) {
            emit(
                DataState.Error(
                    UIComponent.ErrorData(
                        "Failed",
                        e.message ?: "Unknown Error",
                        "Retry"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}
