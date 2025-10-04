package com.example.nativeandroidbasearchitecture.screens.raiserequest

import androidx.compose.runtime.Immutable
import com.example.nativeandroidbasearchitecture.screens.base.ProgressBarState
import com.example.nativeandroidbasearchitecture.screens.base.ViewState
import com.example.nativeandroidbasearchitecture.screens.raiserequest.PartType

@Immutable
data class RaiseRequestViewState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val formOptions: RaiseRequestFormOptions? = null,
    val selectedCarMake: String = "",
    val selectedCarModel: String = "",
    val selectedFuelType: String = "",
    val selectedCarVariant: String = "",
    val selectedDealerLocation: String = "",
    val selectedPolicyType: String = "",
    val dealerName: String = "",
    val advisorName: String = "",
    val advisorContactNumber: String = "",
    val claimNumber: String = "",
    val carRegNumber: String = "",
    val makeYear: String = "",
    val isInventoryPickup: Boolean = false,
    val selectedPartType: String = "alloy_wheels",
    val images: List<String> = emptyList(),
    val submitResult: Boolean? = null,
    val error: String? = null
) : ViewState
