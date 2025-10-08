package com.rego.screens.joinus

import androidx.compose.runtime.Immutable
import com.rego.screens.base.ProgressBarState
import com.rego.screens.base.ViewState

@Immutable
data class JoinUsViewState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val insuranceCompanies: List<String>? = null,
    val companyTypes: List<String>? = null,
) : ViewState