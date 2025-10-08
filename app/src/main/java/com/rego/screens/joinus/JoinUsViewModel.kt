package com.rego.screens.joinus

import androidx.lifecycle.viewModelScope
import com.rego.screens.base.BaseViewModel
import com.rego.screens.base.ProgressBarState
import com.rego.screens.base.UIComponent
import kotlinx.coroutines.launch

class JoinUsViewModel(private val api: JoinUsApi) :
    BaseViewModel<JoinUsEvent, JoinUsViewState, JoinUsAction>() {

    override fun setInitialState() = JoinUsViewState()

    override fun onTriggerEvent(event: JoinUsEvent) {
        when (event) {
            is JoinUsEvent.Init -> loadDropdownData()
            is JoinUsEvent.Submit -> handleSubmit(event)
        }
    }

    private fun loadDropdownData() {
        viewModelScope.launch {
            setState { copy(progressBarState = ProgressBarState.Loading) }
            try {
                val insuranceResponse = api.getInsuranceCompanies()
                val companyTypeResponse = api.getCompanyTypes()
                if (insuranceResponse.status == true && companyTypeResponse.status == true) {
                    setState {
                        copy(
                            insuranceCompanies = insuranceResponse.data,
                            companyTypes = companyTypeResponse.data
                        )
                    }
                } else {
                    throw Exception(
                        insuranceResponse.message ?: companyTypeResponse.message ?: "Unknown Error"
                    )
                }
            } catch (e: Exception) {
                setError {
                    UIComponent.ErrorData(
                        title = "OOPS!",
                        message = e.message ?: "Unknown Error",
                        buttonText = "Retry"
                    )
                }
            } finally {
                setState { copy(progressBarState = ProgressBarState.Idle) }
            }
        }
    }

    private fun handleSubmit(event: JoinUsEvent.Submit) {
        viewModelScope.launch {
            setState { copy(progressBarState = ProgressBarState.Loading) }
            try {
                val response = api.submitJoinRequest(
                    name = event.name,
                    email = event.email,
                    phone = event.phone,
                    city = event.city,
                    state = event.state,
                    insuranceCompany = event.insuranceCompany,
                    companyType = event.companyType
                )
                if (response.status == true) {
                    setAction { JoinUsAction.SubmitSuccess }
                } else {
                    throw Exception(response.message ?: "Unknown Error")
                }
            } catch (e: Exception) {
                setError {
                    UIComponent.ErrorData(
                        title = "OOPS!",
                        message = e.message ?: "Unknown Error",
                        buttonText = "Retry"
                    )
                }
            } finally {
                setState { copy(progressBarState = ProgressBarState.Idle) }
            }
        }
    }
}