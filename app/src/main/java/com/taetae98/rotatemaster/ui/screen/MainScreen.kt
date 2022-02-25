package com.taetae98.rotatemaster.ui.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.taetae98.rotatemaster.R

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Toolbar(navController)
        }
    ) {
        
    }
}

@Composable
private fun Toolbar(navController: NavHostController) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        actions = {
            SettingAction(navController)
        }
    )
}

@Composable
private fun SettingAction(navController: NavHostController) {
    IconButton(
        onClick = {

        }
    ) {
        Icon(imageVector = Icons.Rounded.Settings, contentDescription = stringResource(id = R.string.setting))
    }
}

@Preview
@Composable
private fun Preview() {
    MainScreen(navController = rememberNavController())
}