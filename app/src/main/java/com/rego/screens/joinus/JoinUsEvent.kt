package com.rego.screens.joinus

import com.rego.screens.base.ViewEvent

sealed class JoinUsEvent : ViewEvent {
    object Init : JoinUsEvent()
    data class Submit(
        val name: String,
        val email: String,
        val phone: String,
        val city: String,
        val state: String,
        val insuranceCompany: String,
        val companyType: String,
    ) : JoinUsEvent()
}