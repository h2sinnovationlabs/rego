package com.example.nativeandroidbasearchitecture.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nativeandroidbasearchitecture.R
import com.example.nativeandroidbasearchitecture.screens.components.OrderCard
import com.example.nativeandroidbasearchitecture.screens.components.OrderData
import com.example.nativeandroidbasearchitecture.ui.theme.Color00954D
import com.example.nativeandroidbasearchitecture.ui.theme.Color1A1A1A_16
import com.example.nativeandroidbasearchitecture.ui.theme.Color1A1A1A_40
import com.example.nativeandroidbasearchitecture.ui.theme.Color1A1A1A_60
import com.example.nativeandroidbasearchitecture.ui.theme.Color1A1A1A_90
import com.example.nativeandroidbasearchitecture.ui.theme.NativeAndroidBaseArchitectureTheme
import com.example.nativeandroidbasearchitecture.ui.theme.fontLightPoppins
import com.example.nativeandroidbasearchitecture.ui.theme.fontMediumPoppins
import com.example.nativeandroidbasearchitecture.ui.theme.fontSemiBoldPoppins
import com.example.nativeandroidbasearchitecture.util.SetNavigationBarColor

@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    onRaiseRequest: () -> Unit,
    onGridOptionClick: () -> Unit,
    onOrderClick: () -> Unit,
    onOrderListClick: (String) -> Unit = {},
    onNotificationClick: () -> Unit = {},
) {
    SetNavigationBarColor()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color00954D)
            .statusBarsPadding(),
        topBar = {
            // HEADER - Avatar, greeting, notification bell
            Box(
                modifier = Modifier
                    .background(Color00954D)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Profile circle with menu icon overlay
                    Box(modifier = Modifier.size(42.dp)) {
                        Text(
                            text = "A",
                            modifier = Modifier
                                .size(32.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.White.copy(alpha = 0.24f),
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .background(Color.Transparent, shape = RoundedCornerShape(100.dp)),
                            // Replace fontSemiBold() below with your font semi-bold style if required
                            style = fontSemiBoldPoppins().copy(fontSize = 22.sp),
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        // Menu icon overlay (positioned at bottom-end)
                        Box(
                            modifier = Modifier

                                .align(Alignment.BottomEnd)
                                .size(20.dp)
                                .background(Color.White, shape = RoundedCornerShape(100.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.menu),
                                contentDescription = "Menu",
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Welcome Ayush,",
                            style = fontSemiBoldPoppins().copy(fontSize = 16.sp),
                            color = Color.White
                        )
                    }
                    Icon(
                        painter = painterResource(R.drawable.notification),
                        contentDescription = "Notification",
                        modifier = Modifier
                            .size(22.dp)
                            .clickable {
                                onNotificationClick()
                            },
                        tint = Color.White
                    )
                }
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // content area: we can use innerNav NavHost, but to keep it short, show content based on selection

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                HomeScreenContent(
                    onRaiseRequest = onRaiseRequest,
                    onGridOptionClick = onGridOptionClick,
                    onOrderClick = onOrderClick,
                    onOrderListClick = onOrderListClick,
                    onNotificationClick = onNotificationClick
                )
            }

            BottomNavBar(onProfileClick)
        }
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onRaiseRequest: () -> Unit,
    onGridOptionClick: () -> Unit,
    onOrderClick: () -> Unit,
    onOrderListClick: (String) -> Unit = {},
    onNotificationClick: () -> Unit = {}
) {
    // --- Constants, sample data, utility colors/typography (replace with Type.kt, Color.kt as needed) ---
    // These imports expected for the below code:
    // import androidx.compose.foundation.clickable
    // import androidx.compose.foundation.border
    // import androidx.compose.foundation.layout.*
    // import androidx.compose.foundation.lazy.LazyRow
    // import androidx.compose.foundation.lazy.items
    // import androidx.compose.material3.Divider
    // import androidx.compose.material3.Icon
    // import androidx.compose.material3.Text
    // import androidx.compose.runtime.*
    // import androidx.compose.ui.Modifier
    // import androidx.compose.ui.Alignment
    // import androidx.compose.ui.graphics.Color
    // import androidx.compose.ui.res.painterResource
    // import androidx.compose.ui.unit.dp
    // import androidx.compose.ui.unit.sp
    // import androidx.compose.foundation.shape.RoundedCornerShape
    // You must provide color values if not present (replace Color00954D, Color1A1A1A, etc, with yours)
    // Typography functions types: fontSemiBold(), fontMedium(), fontBold(), fontLight(), fontRegular() etc

    // --- Sample data ---
    val quickFilters = listOf(
        "Work In Progress",
        "Pickup Aligned",
        "Part Delivered",
        "Pickup Done",
        "Invoice Generated",
        "Ready for Delivery"
    )

    val summaryCards = listOf(
        Triple("New Leads", R.drawable.audience, 0),
        Triple("Total Leads", R.drawable.total_leads, 0),
        Triple("Approved", R.drawable.approved, 1),
        Triple("Not Repairable", R.drawable.not_repairable, 0),
        Triple("Completed", R.drawable.completed, 1),
        Triple("Pending", R.drawable.pending, 0)
    )

    data class OngoingOrder(
        val orderId: String,
        val status: String,
        val carMake: String? = null,
        val deliveryDate: String? = null
    )

    // Extended sample for ALL statuses
    val ongoingOrdersAll = listOf(
        OrderData(
            orderId = "12042501",
            status = "Pickup Aligned",
            carMake = "Hyundai i20, 2023",
            deliveryDate = "21/04/25"
        ),
        OrderData(
            orderId = "13042512",
            status = "Pickup Aligned",
            carMake = "Honda City, 2020",
            deliveryDate = "21/02/24"
        ),
        OrderData(
            orderId = "18049231",
            status = "Work In Progress",
            carMake = "Honda City, 2020",
            deliveryDate = "21/02/24"
        ),
        OrderData(
            orderId = "19002451",
            status = "Pickup Done",
            carMake = "Hyundai Verna, 2021",
            deliveryDate = "22/07/24"
        ),
        OrderData(
            orderId = "11049501",
            status = "Part Delivered",
            carMake = "MG Hector, 2022",
            deliveryDate = "29/11/24"
        ),
        OrderData(
            orderId = "12011111",
            status = "Invoice Generated",
            carMake = "Maruti Alto, 2022",
            deliveryDate = "01/12/24"
        ),
        OrderData(
            orderId = "19005001",
            status = "Ready for Delivery",
            carMake = "Suzuki Baleno, 2021",
            deliveryDate = "10/10/24"
        )
    )

    // State for expand/collapse ongoing order card and filters
    var expandedCard by remember { mutableStateOf<String?>(null) }
    var selectedQuickFilter by remember { mutableStateOf<String?>(null) }

    // Filter orders
    val ongoingOrders = ongoingOrdersAll.filter {
        selectedQuickFilter == null || it.status == selectedQuickFilter
    }

    Box(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    color = Color00954D,
                    shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            // SEARCH BAR
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .height(48.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.search),
                        contentDescription = "Search",
                        modifier = Modifier.size(18.dp),
                        tint = Color(0xFF00954D)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Search claim number etc",
                        style = fontMediumPoppins().copy(fontSize = 12.sp),
                        color = Color1A1A1A_40()
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // RAISE REQUEST CARD
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { onRaiseRequest() }
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.White,
                                Color(0xFFCAFFE5)
                            )
                        ), RoundedCornerShape(12.dp)
                    )
                    .height(70.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.raise_request),
                        contentDescription = "Raise Request",
                        modifier = Modifier.size(52.dp),
                        tint = Color.Unspecified,
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp)
                    ) {
                        Text(
                            text = "Raise a request",
                            style = fontSemiBoldPoppins().copy(fontSize = 14.sp),
                            color = Color1A1A1A_90()
                        )
                        Text(
                            text = "Send request to REGO CRs for part repairs",
                            style = fontMediumPoppins().copy(fontSize = 10.sp),
                            color = Color1A1A1A_60()
                        )
                    }
                    Icon(
                        painter = painterResource(R.drawable.back),
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .size(20.dp)
                            .rotate(180f),
                        tint = Color00954D
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // ONGOING ORDERS SECTION
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(800.dp)
            ) {
                item {
                    // SUMMARY CARDS GRID - 2x2 grid layout
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            summaryCards.take(2).forEach { (label, iconRes, value) ->
                                SummaryCard(
                                    label = label,
                                    iconRes = iconRes,
                                    value = value,
                                    onClick = { onOrderListClick(label) },
                                    modifier = Modifier
                                        .weight(1f)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            summaryCards.subList(2, 4).forEach { (label, iconRes, value) ->
                                SummaryCard(
                                    label = label,
                                    iconRes = iconRes,
                                    value = value,
                                    onClick = { onOrderListClick(label) },
                                    modifier = Modifier
                                        .weight(1f)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            summaryCards.subList(4, 6).forEach { (label, iconRes, value) ->
                                SummaryCard(
                                    label = label,
                                    iconRes = iconRes,
                                    value = value,
                                    onClick = { onOrderListClick(label) },
                                    modifier = Modifier
                                        .weight(1f)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(26.dp))
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.LightGray,
                    )
                }
                item {
                    Column(
                        modifier = Modifier
                            .background(color = Color.White)
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = "Ongoing Orders",
                                style = fontSemiBoldPoppins().copy(fontSize = 16.sp),
                                color = Color(0xE61A1A1A)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "(${ongoingOrders.size})",
                                style = fontMediumPoppins().copy(fontSize = 15.sp),
                                color = Color(0xFFFF514F)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "View All",
                                style = fontMediumPoppins().copy(fontSize = 12.sp),
                                color = Color(0xFF00954D),
                                modifier = Modifier.clickable { onOrderListClick("Ongoing Orders") }
                            )
                        }
                        Text(
                            text = "Manage all your order in one go.",
                            style = fontLightPoppins().copy(fontSize = 12.sp),
                            color = Color(0x991A1A1A),
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Color.LightGray
                        )
                    }
                }

                item {
                    // Quick Filters Row (selectable)
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
                        items(quickFilters) { filter ->
                            val selected = filter == selectedQuickFilter
                            Box(
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .background(
                                        if (selected) Color00954D else Color.White,
                                        RoundedCornerShape(21.dp)
                                    )
                                    .border(1.dp, Color1A1A1A_16(), RoundedCornerShape(21.dp))
                                    .clickable {
                                        selectedQuickFilter = if (selected) null else filter
                                    }
                            ) {
                                Text(
                                    text = filter,
                                    style = fontMediumPoppins().copy(fontSize = 10.sp),
                                    color = if (selected) Color.White else Color1A1A1A_60(),
                                    modifier = Modifier.padding(horizontal = 13.dp, vertical = 7.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Ongoing Order Cards
                items(ongoingOrders) { order ->
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        OrderCard(
                            order = order,
                            orderType = "Ongoing Order",
                            isExpanded = expandedCard == order.orderId,
                            onToggleExpanded = {
                                expandedCard =
                                    if (expandedCard == order.orderId) null else order.orderId
                            },
                            onCardClick = { onOrderClick() },
                        )
                        Spacer(modifier = Modifier.height(11.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun SummaryCard(
    label: String,
    iconRes: Int,
    value: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(94.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = label,
                    style = fontMediumPoppins().copy(fontSize = 12.sp),
                    color = Color1A1A1A_60()
                )
                androidx.compose.foundation.Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = label,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$value",
                    style = fontSemiBoldPoppins().copy(fontSize = 24.sp),
                    color = Color.Black
                )
                Spacer(Modifier.width(14.dp))
                Icon(
                    painter = painterResource(R.drawable.back),
                    contentDescription = "Arrow",
                    modifier = Modifier
                        .size(13.dp)
                        .rotate(180f),
                    tint = Color00954D
                )
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 800)
@Composable
fun HomeScreenContentPreview() {
    NativeAndroidBaseArchitectureTheme {
        HomeScreenContent(
            onRaiseRequest = {},
            onOrderClick = {},
            onGridOptionClick = {},
            onOrderListClick = {}
        )
    }
}

@Composable
fun BottomNavBar(
    onProfileClick: () -> Unit,
) {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { /** Already at home*/ },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.home),
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") })
        NavigationBarItem(
            selected = false,
            onClick = onProfileClick,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.person),
                    contentDescription = "Profile",
                )
            },
            label = { Text("Profile") })
    }
}