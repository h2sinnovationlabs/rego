package com.example.nativeandroidbasearchitecture.screens.orderdetails

import com.example.nativeandroidbasearchitecture.screens.components.OrderData
import com.example.nativeandroidbasearchitecture.screens.orderdetails.OrderDetails

interface OrderDetailsApi {
    suspend fun getOrderList(): Map<Int, List<OrderData>>
    suspend fun getOrderDetails(orderId: String): OrderDetails
}
