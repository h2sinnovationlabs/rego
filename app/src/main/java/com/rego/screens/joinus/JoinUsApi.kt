package com.rego.screens.joinus

import com.rego.CommonResponse

interface JoinUsApi {
    fun getInsuranceCompanies(): CommonResponse<List<String>>
    fun getCompanyTypes(): CommonResponse<List<String>>
    fun submitJoinRequest(
        name: String,
        email: String,
        phone: String,
        city: String,
        state: String,
        insuranceCompany: String,
        companyType: String
    ): CommonResponse<Unit>
}