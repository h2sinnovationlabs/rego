package com.example.nativeandroidbasearchitecture.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nativeandroidbasearchitecture.screens.joinus.JoinUsParentScreen
import com.example.nativeandroidbasearchitecture.screens.loginoption.LoginOptionScreen
import com.example.nativeandroidbasearchitecture.screens.main.HomeScreen
import com.example.nativeandroidbasearchitecture.screens.main.ProfileScreen
import com.example.nativeandroidbasearchitecture.screens.mobileverification.MobileVerificationScreen
import com.example.nativeandroidbasearchitecture.screens.notifications.NotificationScreen
import com.example.nativeandroidbasearchitecture.screens.orderdetails.OrderDetailsScreen
import com.example.nativeandroidbasearchitecture.screens.orderdetails.OrderListScreen
import com.example.nativeandroidbasearchitecture.screens.raiserequest.RaiseRequestParentScreen
import com.example.nativeandroidbasearchitecture.screens.setpassword.SetPasswordParentScreen
import kotlinx.coroutines.delay

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.Splash.route) {
        composable(Destinations.Splash.route) {
            // Splash should show once and auto navigate after 2s
            LaunchedEffect(Unit) {
                delay(2000)
                navController.navigate(Destinations.Home.route) {
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
            MobileVerificationScreen(
                onVerificationComplete = {
                    navController.navigate(Destinations.Home.route) {
                        popUpTo(Destinations.LoginOptions.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Destinations.Signup.route) {
            JoinUsParentScreen(
                onBack = {
                    navController.popBackStack()
                },
                onDone = {
                    navController.navigate(Destinations.Home.route) {
                        popUpTo(Destinations.LoginOptions.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Destinations.Home.route) {
            HomeScreen(
                onProfileClick = {

                },
                onRaiseRequest = {
                    navController.navigate(Destinations.RaiseRequest.route)
                },
                onGridOptionClick = {

                },
                onOrderClick = {
                    navController.navigate(Destinations.OrderDetails.route)
                },
                onOrderListClick = {
                    navController.navigate(Destinations.OrdersList.createRoute(it))
                },
                onNotificationClick = {
                    navController.navigate(Destinations.Notification.route)
                }
            )
        }

        composable(Destinations.RaiseRequest.route) {
            RaiseRequestParentScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Destinations.OrdersList.route) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: "default"
            OrderListScreen(
                orderType = type,
                onOrderClick = { orderId ->
                    navController.navigate(Destinations.OrderDetails.createRoute(orderId))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Destinations.OrderDetails.route) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: "-1"
            OrderDetailsScreen(
                orderId = orderId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Destinations.Notification.route) { backStackEntry ->
            NotificationScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Destinations.Profile.route) { backStackEntry ->
            ProfileScreen(
                onChangePasswordClick = {
                    navController.navigate(Destinations.ResetPassword.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Destinations.ResetPassword.route) { backStackEntry ->
            SetPasswordParentScreen(
                onLoginClick = {
                    navController.navigate(Destinations.LoginOptions.route)
                }
            )
        }
    }
}