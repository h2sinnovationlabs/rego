package com.rego.screens.raiserequest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rego.R
import com.rego.ui.theme.Color1A1A1A_90
import com.rego.ui.theme.NativeAndroidBaseArchitectureTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.statusBarsPadding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RaiseRequestParentScreen(
    onBack: () -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onBack() }
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Raise a Request",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color1A1A1A_90()
                )
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            userScrollEnabled = false // Disable manual scrolling
        ) { page ->
            when (page) {
                0 -> {
                    // Remove topBar from RaiseRequestScreen since parent has it
                    RaiseRequestScreen {

                    }
                }

                1 -> {
                    RequestSubmittedScreen(
                        onOkayClick = {
                            onBack()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RaiseRequestParentScreenPreview() {
    NativeAndroidBaseArchitectureTheme {
        RaiseRequestParentScreen()
    }
}