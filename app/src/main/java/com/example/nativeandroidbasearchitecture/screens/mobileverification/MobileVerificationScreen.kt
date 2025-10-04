package com.example.nativeandroidbasearchitecture.screens.mobileverification

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nativeandroidbasearchitecture.R
import com.example.nativeandroidbasearchitecture.screens.components.OtpInputViewType2
import com.example.nativeandroidbasearchitecture.screens.components.RegoButton
import com.example.nativeandroidbasearchitecture.ui.theme.Color00954D
import com.example.nativeandroidbasearchitecture.ui.theme.Color1A1A1A
import com.example.nativeandroidbasearchitecture.ui.theme.NativeAndroidBaseArchitectureTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileVerificationScreen(
    onVerificationComplete: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 2 }, initialPage = 0)
    val coroutineScope = rememberCoroutineScope()

    var mobileNumber by remember { mutableStateOf("") }
    var resendTimer by remember { mutableIntStateOf(20) }
    var isResendEnabled by remember { mutableStateOf(false) }

    // Timer for resend OTP
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage == 1) {
            resendTimer = 20
            isResendEnabled = false
            while (resendTimer > 0) {
                delay(1000)
                resendTimer--
            }
            isResendEnabled = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color00954D)
    ) {
        // Logo at top
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.rego_brandmark),
                contentDescription = "Rego Logo",
                tint = Color.White
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 305.dp, start = 10.dp, end = 10.dp)
                .height(65.dp)
                .background(
                    color = Color.White.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(20.dp)
                )
        )
        // Main content with rounded top corners
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 320.dp)
                .background(
                    Color.White,
                    RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    0 -> MobileNumberInputScreen(
                        mobileNumber = mobileNumber,
                        onMobileNumberChange = { mobileNumber = it },
                        onGetOtpClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                    )

                    1 -> OtpVerificationScreen(
                        mobileNumber = mobileNumber,
                        resendTimer = resendTimer,
                        isResendEnabled = isResendEnabled,
                        onResendClick = {
                            // Reset timer
                            resendTimer = 20
                            isResendEnabled = false
                        },
                        onVerifyClick = onVerificationComplete
                    )
                }
            }
        }
    }
}

@Composable
private fun MobileNumberInputScreen(
    mobileNumber: String,
    onMobileNumberChange: (String) -> Unit,
    onGetOtpClick: () -> Unit
) {
    val isValidMobile = mobileNumber.length == 10 && mobileNumber.all { it.isDigit() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Welcome",
            color = Color00954D,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Enter your phone number to continue",
            color = Color1A1A1A.copy(alpha = 0.87f),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Mobile number input field
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(
                    width = 1.dp,
                    color = Color.Black.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "+91  |",
                fontSize = 14.sp,
                color = Color1A1A1A.copy(alpha = 0.87f),
                modifier = Modifier
                    .background(Color.Transparent),
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = mobileNumber,
                onValueChange = { value ->
                    if (value.length <= 10 && value.all { it.isDigit() }) {
                        onMobileNumberChange(value)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        "Enter Mobile Number",
                        color = Color1A1A1A.copy(alpha = 0.4f)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(color = Color1A1A1A.copy(alpha = 0.9f))
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Get OTP Button
        RegoButton(
            onClick = onGetOtpClick,
            text = "Get OTP",
            enabled = isValidMobile,
            height = 44.dp
        )
    }
}

@Preview(showBackground = true, heightDp = 800)
@Composable
fun MobileVerificationScreenPreview() {
    NativeAndroidBaseArchitectureTheme {
        MobileVerificationScreen(
            onVerificationComplete = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MobileInputScreenPreview() {
    NativeAndroidBaseArchitectureTheme {
        MobileNumberInputScreen(mobileNumber = "9876543210", onMobileNumberChange = {}) { }
    }
}

@Preview(showBackground = true)
@Composable
fun OtpVerificationScreenPreview() {
    NativeAndroidBaseArchitectureTheme {
        OtpVerificationScreen(
            mobileNumber = "9876543210",
            resendTimer = 20,
            isResendEnabled = true,
            onResendClick = {},
            onVerifyClick = {}
        )
    }
}


@SuppressLint("DefaultLocale")
@Composable
private fun OtpVerificationScreen(
    mobileNumber: String,
    resendTimer: Int,
    isResendEnabled: Boolean,
    onResendClick: () -> Unit,
    onVerifyClick: () -> Unit
) {
    var otp by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Verification Code",
            color = Color00954D,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Please enter the verification code sent to",
            color = Color1A1A1A.copy(alpha = 0.6f),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = "+91$mobileNumber",
            color = Color1A1A1A.copy(alpha = 0.87f),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // OTP Input
        OtpInputViewType2(
            otpLength = 6,
            onOtpComplete = { completedOtp ->
                otp = completedOtp
            },
            onCurrentOtpLength = { currentLength ->
                // Handle current OTP length if needed
            },
            onDoneClick = { completedOtp ->

            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Resend code section
        if (isResendEnabled) {
            TextButton(onClick = onResendClick) {
                Text(
                    text = "Resend Code",
                    color = Color00954D,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            Text(
                text = "Resend code in 0:${String.format("%02d", resendTimer)}",
                color = Color1A1A1A.copy(alpha = 0.87f),
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Verify Button
        RegoButton(
            onClick = onVerifyClick,
            text = "Sign In",
            enabled = otp.length == 6,
            height = 44.dp
        )
    }
}