package com.example.nativeandroidbasearchitecture.screens.notifications

interface NotificationApi {
    suspend fun getNotifications(): List<NotificationItem>
}
