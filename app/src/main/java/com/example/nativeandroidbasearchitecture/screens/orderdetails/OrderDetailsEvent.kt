package com.example.nativeandroidbasearchitecture.screens.orderdetails

import com.example.nativeandroidbasearchitecture.screens.base.ViewEvent
import com.example.nativeandroidbasearchitecture.screens.components.OrderData

sealed class OrderDetailsEvent : ViewEvent {
    object Init : OrderDetailsEvent()
    data class LoadOrders(val partTypeIndex: Int) : OrderDetailsEvent()
    data class SelectOrder(val orderId: String) : OrderDetailsEvent()
    data class LoadOrderDetails(val orderId: String) : OrderDetailsEvent()
}
