package com.taetae98.rotatemaster.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.taetae98.rotatemaster.ui.screen.MainScreen

@Preview
@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController()
) {
    val screens = listOf(
        MainScreen
    )

    NavHost(navController = navController, startDestination = screens.first().route) {
        screens.forEach { screen ->
            composable(screen.route) {
                screen.Compose(
                    navController = navController
                )
            }
        }
    }
}