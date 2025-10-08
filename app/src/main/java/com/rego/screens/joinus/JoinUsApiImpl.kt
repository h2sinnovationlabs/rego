package com.rego.screens.joinus

import com.rego.CommonResponse

class JoinUsApiImpl(
    /** ktor http client */
) : JoinUsApi {
    override fun getInsuranceCompanies(): CommonResponse<List<String>> {
        val options = listOf("Company A", "Company B", "Company C")
        return CommonResponse(data = options, status = true, message = "success")
    }

    override fun getCompanyTypes(): CommonResponse<List<String>> {
        val options = listOf("Type 1", "Type 2", "Type 3")
        return CommonResponse(data = options, status = true, message = "success")
    }

    override fun submitJoinRequest(
        name: String,
        email: String,
        phone: String,
        city: String,
        state: String,
        insuranceCompany: String,
        companyType: String
    ): CommonResponse<Unit> {
        return CommonResponse(data = Unit, status = true, message = "success")
    }
}