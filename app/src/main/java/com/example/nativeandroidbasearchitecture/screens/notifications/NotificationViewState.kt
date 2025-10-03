package com.example.nativeandroidbasearchitecture.screens.notifications

import androidx.compose.runtime.Immutable
import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import com.example.nativeandroidbasearchitecture.screens.base.ViewState

@Immutable
data class NotificationViewState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val notifications: List<NotificationItem>? = null
) : ViewState
