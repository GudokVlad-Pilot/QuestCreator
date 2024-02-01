package com.example.questcreator.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login")
    object LandingScreen : Screen("landing")
    object CreatorLoginScreen : Screen("creator_login")
    object CreatorSignUpScreen : Screen("creator_sign_up")
    object CreatorConfirmationScreen : Screen("creator_confirmation")
}