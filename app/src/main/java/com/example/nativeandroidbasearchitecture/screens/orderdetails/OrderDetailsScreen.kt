package com.example.nativeandroidbasearchitecture.screens.orderdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nativeandroidbasearchitecture.R
import com.example.nativeandroidbasearchitecture.screens.components.DashedDivider
import com.example.nativeandroidbasearchitecture.ui.theme.Color1A1A1A_60
import com.example.nativeandroidbasearchitecture.ui.theme.Color1A1A1A_90
import com.example.nativeandroidbasearchitecture.ui.theme.NativeAndroidBaseArchitectureTheme
import com.example.nativeandroidbasearchitecture.ui.theme.fontSemiBoldPoppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailsScreen(orderId: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(elevation = 4.dp),
                title = {
                    Text(
                        text = "Order details",
                        style = fontSemiBoldPoppins().copy(
                            fontSize = 16.sp,
                            color = Color1A1A1A_90()
                        )
                    )
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .clickable {
                                onBack()
                            }
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(innerPadding)
        ) {
            Spacer(Modifier.height(18.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Order ID: ",
                    style = fontSemiBoldPoppins().copy(
                        fontSize = 12.sp,
                        color = Color.Black.copy(alpha = 0.6f)
                    )
                )
                Text(
                    text = orderId,
                    style = fontSemiBoldPoppins().copy(
                        fontSize = 14.sp,
                        color = Color.Black.copy(alpha = 0.9f)
                    )
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            DashedDivider()
            Spacer(Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Part Type:",
                    style = fontSemiBoldPoppins().copy(fontSize = 14.sp, color = Color1A1A1A_90())
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.alloy_wheel),
                    contentDescription = "Alloy Wheel",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Alloy Wheel",
                    style = fontSemiBoldPoppins().copy(fontSize = 12.sp, color = Color1A1A1A_90())
                )
            }
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Part Photos:",
                style = fontSemiBoldPoppins().copy(fontSize = 14.sp, color = Color1A1A1A_90())
            )
            Spacer(Modifier.height(8.dp))
            PartPhotoGrid()
            Spacer(Modifier.height(8.dp))
            OrderSummaryCard()
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun PartPhotoGrid() {
    val images = List(8) { R.drawable.part_photo }
    Column {
        for (row in 0..1) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                for (col in 0..3) {
                    Image(
                        painter = painterResource(id = images[row * 4 + col]),
                        contentDescription = "Part Photo",
                        modifier = Modifier
                            .weight(1f)
                            .height(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
fun TwoColumnLabelRow(
    label1: String,
    label2: String,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: Int = 14,
    color: Color = Color.Gray
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label1,
            style = fontSemiBoldPoppins().copy(fontSize = 12.sp, color = Color1A1A1A_60())
        )
        Text(
            text = label2,
            style = fontSemiBoldPoppins().copy(fontSize = 12.sp, color = Color1A1A1A_60())
        )
    }
}

@Composable
fun TwoColumnValueRow(
    value1: String,
    value2: String,
    value2Color: Color = Color1A1A1A_90()
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = value1,
            style = fontSemiBoldPoppins().copy(fontSize = 12.sp, color = Color1A1A1A_90())
        )
        Text(
            text = value2,
            style = fontSemiBoldPoppins().copy(fontSize = 12.sp, color = value2Color)
        )
    }
}

@Composable
fun OrderSummaryCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TwoColumnLabelRow(
                label1 = "Car Name",
                label2 = "Car Registration Number"
            )
            Spacer(Modifier.height(2.dp))
            TwoColumnValueRow(
                value1 = "Hyundai Creta, 2023",
                value2 = "HR 26 EX 9342"
            )
            Spacer(modifier = Modifier.height(14.dp))
            TwoColumnLabelRow(
                label1 = "Advisor Name",
                label2 = "Advisor Contact Number"
            )
            Spacer(Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Suresh Singh",
                    style = fontSemiBoldPoppins().copy(fontSize = 12.sp, color = Color1A1A1A_90())
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.call),
                        contentDescription = "WhatsApp",
                        modifier = Modifier.size(18.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        "9954332342",
                        style = fontSemiBoldPoppins().copy(
                            fontSize = 12.sp,
                            color = Color1A1A1A_90()
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
            TwoColumnLabelRow(
                label1 = "Dealer Name",
                label2 = "Dealer Location"
            )
            Spacer(Modifier.height(2.dp))
            TwoColumnValueRow(
                value1 = "Prem Motors",
                value2 = "Sector 18, Gurgoan, Haryana"
            )
            Spacer(modifier = Modifier.height(14.dp))
            TwoColumnLabelRow(
                label1 = "Claim Number",
                label2 = "Reason for rejection"
            )
            Spacer(Modifier.height(2.dp))
            TwoColumnValueRow(
                value1 = "245577545242",
                value2 = "Pickup denied by Workshop",
                value2Color = Color(0xFFE73C33)
            )
        }
    }
}

@Preview
@Composable
fun PreviewOrderCard() {
    NativeAndroidBaseArchitectureTheme {
        OrderDetailsScreen(orderId = "12345", onBack = {})
    }
}