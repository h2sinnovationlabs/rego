package com.rego.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rego.screens.joinus.JoinUsParentScreen
import com.rego.screens.loginoption.LoginOptionScreen
import com.rego.screens.main.home.HomeScreen
import com.rego.screens.main.profile.ProfileScreen
import com.rego.screens.mobileverification.MobileVerificationScreen
import com.rego.screens.notifications.NotificationScreen
import com.rego.screens.orderdetails.OrderDetailsScreen
import com.rego.screens.orderdetails.OrderListScreen
import com.rego.screens.raiserequest.RaiseRequestParentScreen
import com.rego.screens.setpassword.SetPasswordParentScreen
import com.rego.screens.splash.SplashScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.Splash.route) {
        composable(Destinations.Splash.route) {
            SplashScreen {
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
                    if (navController.currentDestination?.route != Destinations.Profile.route) {
                        navController.navigate(Destinations.Profile.route)
                    }
                },
                onRaiseRequest = {
                    navController.navigate(Destinations.RaiseRequest.route)
                },
                onGridOptionClick = {

                },
                onOrderClick = {
                    navController.navigate(Destinations.OrderDetails.route)
                },
                onSearchClick = {
                    // Open search screen
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
                },
                onHomeClick = {
                    if (navController.currentDestination?.route == Destinations.Profile.route) {
                        navController.popBackStack()
                    }
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