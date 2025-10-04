package com.example.nativeandroidbasearchitecture.screens.main.home

import androidx.compose.runtime.Immutable
import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import com.example.nativeandroidbasearchitecture.screens.base.ViewState
import com.example.nativeandroidbasearchitecture.screens.components.OrderData

@Immutable
data class HomeViewState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val summaryCards: List<Triple<String, Int, Int>>? = null,
    val ongoingOrdersAll: List<OrderData>? = null,
    val quickFilters: List<String>? = null,
) : ViewState