package com.example.nativeandroidbasearchitecture.screens.orderdetails

import androidx.compose.runtime.Immutable
import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import com.example.nativeandroidbasearchitecture.screens.base.ViewState
import com.example.nativeandroidbasearchitecture.screens.components.OrderData

@Immutable
data class OrderDetailsViewState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val orderListByType: Map<Int, List<OrderData>>? = null,
    val selectedOrderId: String? = null,
    val selectedOrderDetails: OrderDetails? = null
) : ViewState
