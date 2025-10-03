package com.example.nativeandroidbasearchitecture.screens.joinus

import androidx.compose.runtime.Immutable
import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import com.example.nativeandroidbasearchitecture.screens.base.ViewState

@Immutable
data class JoinUsViewState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val insuranceCompanies: List<String>? = null,
    val companyTypes: List<String>? = null,
) : ViewState