package com.example.nativeandroidbasearchitecture.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nativeandroidbasearchitecture.screens.loginoption.LoginOptionScreen
import com.example.nativeandroidbasearchitecture.screens.main.MainScreen
import kotlinx.coroutines.delay

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.Splash.route) {
        composable(Destinations.Splash.route) {
            // Splash should show once and auto navigate after 2s
            LaunchedEffect(Unit) {
                delay(2000)
                navController.navigate(Destinations.LoginOptions.route) {
                    popUpTo(Destinations.Splash.route) { inclusive = true }
                }
            }
        }

        composable(Destinations.LoginOptions.route) {
            LoginOptionScreen(
                onLogin = { navController.navigate(Destinations.Login.route) },
                onSignUp = { navController.navigate(Destinations.Signup.route) }
            )
        }

        composable(Destinations.Login.route) {
            com.example.composekoinnav.screens.auth.LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Destinations.Main.route) {
                        popUpTo(Destinations.LoginOptions.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Destinations.Signup.route) {
            com.example.composekoinnav.screens.auth.SignUpScreen(
                onSignUpComplete = {
                    // after signup navigate to login
                    navController.navigate(Destinations.Login.route) {
                        popUpTo(Destinations.Signup.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Destinations.Home.route) {
            MainScreen(
                onHomeClick = {

                },
                onProfileClick = {

                },
                onRaiseRequest = {

                },
                onGridOptionClick = {

                },
                onOrderClick = {

                })
        }

        composable(Destinations.RaiseRequest.route) {
            com.example.composekoinnav.screens.home.RaiseRequestScreen(onSubmit = {
                navController.popBackStack()
            })
        }

        composable(Destinations.OrdersList.route) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: "default"
            com.example.composekoinnav.screens.orders.OrdersListScreen(
                type = type,
                onOrderClick = { orderId ->
                    navController.navigate(Destinations.OrderDetails.createRoute(orderId))
                })
        }

        composable(Destinations.OrderDetails.route) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: "-1"
            com.example.composekoinnav.screens.orders.OrderDetailsScreen(orderId = orderId)
        }
    }
}