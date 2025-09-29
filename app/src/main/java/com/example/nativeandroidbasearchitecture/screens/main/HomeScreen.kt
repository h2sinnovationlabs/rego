package com.example.nativeandroidbasearchitecture.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.nativeandroidbasearchitecture.R

@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    onRaiseRequest: () -> Unit,
    onGridOptionClick: () -> Unit,
    onOrderClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // content area: we can use innerNav NavHost, but to keep it short, show content based on selection
        HomeScreenContent(
            onRaiseRequest = onRaiseRequest,
            onGridOptionClick = onGridOptionClick,
            onOrderClick = onOrderClick
        )
        BottomNavBar(onProfileClick)
    }
}

@Composable
fun HomeScreenContent(
    onRaiseRequest: () -> Unit,
    onGridOptionClick: () -> Unit,
    onOrderClick: () -> Unit
) {

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
                    painter = painterResource(R.drawable.ic_arrow),
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") })
        NavigationBarItem(
            selected = false,
            onClick = onProfileClick,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow),
                    contentDescription = "Profile"
                )
            },
            label = { Text("Profile") })
    }
}