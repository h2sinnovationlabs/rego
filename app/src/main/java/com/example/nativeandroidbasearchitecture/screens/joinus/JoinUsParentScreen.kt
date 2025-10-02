package com.example.nativeandroidbasearchitecture.screens.joinus

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nativeandroidbasearchitecture.R
import com.example.nativeandroidbasearchitecture.screens.components.DropdownField
import com.example.nativeandroidbasearchitecture.screens.components.RegoButton
import com.example.nativeandroidbasearchitecture.screens.components.TransparentInputField
import com.example.nativeandroidbasearchitecture.ui.theme.Color00954D
import com.example.nativeandroidbasearchitecture.ui.theme.Color1A1A1A
import com.example.nativeandroidbasearchitecture.ui.theme.Color1A1A1A_60
import kotlinx.coroutines.launch

@Composable
fun JoinUsParentScreen(onBack: () -> Unit, onDone: () -> Unit = {}) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()
    Scaffold(modifier = Modifier.navigationBarsPadding()) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(start = 8.dp, top = 12.dp, end = 8.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    tint = Color1A1A1A.copy(alpha = 0.9f),
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp)
                        .padding(4.dp)
                        .padding(end = 4.dp)
                        .clickable { onBack() }
                )
                Text(
                    text = "Join Us",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .background(Color.Gray)
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    0 -> JoinUsFormScreen(
                        onSubmit = {
                            coroutineScope.launch { pagerState.animateScrollToPage(1) }
                        }
                    )

                    1 -> JoinUsSuccessScreen(onOkay = onDone)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JoinUsParentScreenPreview() {
    JoinUsParentScreen(onBack = {})
}

@Composable
private fun JoinUsFormScreen(onSubmit: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var insuranceCompany by remember { mutableStateOf("") }
    var isInsuranceDropdown by remember { mutableStateOf(false) }
    val insuranceOptions = listOf("Company A", "Company B", "Company C")
    var companyType by remember { mutableStateOf("") }
    var isCompanyTypeDropdown by remember { mutableStateOf(false) }
    val companyTypeOptions =
        listOf("Type 1", "Type 2", "Type 3", "Type 3", "Type 3", "Type 3", "Type 3")

    // Email validation using regex
    fun isValidEmail(email: String): Boolean {
        val regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return regex.matches(email)
    }

    // Phone validation: only digits, and 10 digits
    fun isValidPhone(phone: String): Boolean =
        phone.length == 10 && phone.all { it.isDigit() }

    val isFormValid =
        name.isNotBlank() && isValidEmail(email) && isValidPhone(phone) && city.isNotBlank() && state.isNotBlank() && insuranceCompany.isNotBlank() && companyType.isNotBlank()
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 18.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Become a Insurance partner today!",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color1A1A1A,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Fill out the form and our team will get back to you.",
            color = Color1A1A1A_60(),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, bottom = 18.dp)
        )
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(18.dp)
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                TransparentInputField(
                    label = "Name",
                    value = name,
                    onValueChange = { name = it },
                    leadingIcon = R.drawable.person,
                    placeholder = "Enter Name"
                )
                TransparentInputField(
                    label = "Official Email ID",
                    value = email,
                    onValueChange = { email = it },
                    leadingIcon = R.drawable.email,
                    placeholder = "Enter Official E-mail Id",
                    keyboardType = KeyboardType.Email
                )
                TransparentInputField(
                    label = "Phone Number",
                    value = phone,
                    onValueChange = { phone = it.filter { ch -> ch.isDigit() }.take(10) },
                    leadingIcon = R.drawable.phone,
                    placeholder = "Enter Phone number",
                    keyboardType = KeyboardType.Phone
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TransparentInputField(
                        label = "City",
                        value = city,
                        onValueChange = { city = it },
                        leadingIcon = R.drawable.location,
                        placeholder = "Enter City",
                        modifier = Modifier.weight(1f)
                    )
                    TransparentInputField(
                        label = "State",
                        value = state,
                        onValueChange = { state = it },
                        leadingIcon = R.drawable.location,
                        placeholder = "Enter State",
                        modifier = Modifier.weight(1f)
                    )
                }
                DropdownField(
                    label = "Insurance company",
                    value = insuranceCompany,
                    onValueChange = { insuranceCompany = it },
                    onDropdownExpand = { isInsuranceDropdown = true },
                    expanded = isInsuranceDropdown,
                    options = insuranceOptions,
                    leadingIcon = R.drawable.location,
                    placeholder = "Select Insurance Company",
                    onDismissRequest = { isInsuranceDropdown = false }
                )
                DropdownField(
                    label = "Company Type",
                    value = companyType,
                    onValueChange = { companyType = it },
                    onDropdownExpand = { isCompanyTypeDropdown = true },
                    expanded = isCompanyTypeDropdown,
                    options = companyTypeOptions,
                    leadingIcon = R.drawable.location,
                    placeholder = "Select Company Type",
                    onDismissRequest = { isCompanyTypeDropdown = false }
                )
            }

        }
        Spacer(modifier = Modifier.height(40.dp))
        RegoButton(
            onClick = onSubmit,
            text = "Submit",
            enabled = isFormValid
        )
    }
}

@Preview(showBackground = true)
@Composable
fun JoinUsFormScreenPreview() {
    JoinUsFormScreen(onSubmit = {})
}

@Composable
private fun JoinUsSuccessScreen(onOkay: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(88.dp)
                .background(Color00954D, shape = CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.tick),
                contentDescription = "Success",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Response Submitted",
            color = Color1A1A1A,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Thank you for your interest. We will\nreach out to you within the next 24 hours.",
            color = Color1A1A1A_60(),
            fontSize = 12.sp,
            modifier = Modifier.padding(vertical = 10.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(56.dp))
        RegoButton(
            onClick = onOkay,
            text = "Okay"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun JoinUsSuccessScreenPreview() {
    JoinUsSuccessScreen(onOkay = {})
}
