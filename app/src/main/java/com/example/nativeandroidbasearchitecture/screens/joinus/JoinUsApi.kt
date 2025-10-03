package com.example.nativeandroidbasearchitecture.screens.joinus

import com.example.nativeandroidbasearchitecture.CommonResponse

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