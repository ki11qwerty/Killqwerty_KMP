package com.killqwerty.killqwerty_kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.killqwerty.killqwerty_kmp.ui.screens.main.MainScreen
import com.killqwerty.killqwerty_kmp.ui.screens.splash.SplashScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") {
                SplashScreen(onNavigateToMain = {
                    navController.navigate("main") {
                        popUpTo("splash") { inclusive = true }
                    }
                })
            }
            composable("main") {
                MainScreen()
            }
        }
    }
}