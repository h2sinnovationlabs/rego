package com.rego.navigation

sealed class Destinations(val route: String) {
    object Splash : Destinations("splash")
    object LoginOptions : Destinations("login_options")
    object Login : Destinations("login")
    object Signup : Destinations("signup")

    // nested under main
    object Home : Destinations("home")
    object Profile : Destinations("profile")
    object Notification : Destinations("notification")
    object ResetPassword : Destinations("reset_password")
    object RaiseRequest : Destinations("raise_request")
    object OrdersList : Destinations("orders_list/{type}") {
        fun createRoute(type: String) = "orders_list/$type"
    }
    object OrderDetails : Destinations("order_details/{orderId}") {
        fun createRoute(orderId: String) = "order_details/$orderId"
    }
}