package com.taetae98.rotatemaster.protocol

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface NavigationScreen {
    val route: String

    @Composable
    fun Compose(navController: NavHostController)
}