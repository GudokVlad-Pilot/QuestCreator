package com.example.questcreator.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login")
    object LandingScreen : Screen("landing")
    object CreatorScreen : Screen("creator")
}