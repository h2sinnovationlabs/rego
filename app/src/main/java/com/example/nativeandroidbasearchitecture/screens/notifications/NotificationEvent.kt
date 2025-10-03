package com.example.nativeandroidbasearchitecture.screens.notifications

import com.example.nativeandroidbasearchitecture.screens.base.ViewEvent

sealed class NotificationEvent : ViewEvent {
    object Init : NotificationEvent()
    data class NotificationClicked(val notification: NotificationItem) : NotificationEvent()
    // Add more events as needed
}
