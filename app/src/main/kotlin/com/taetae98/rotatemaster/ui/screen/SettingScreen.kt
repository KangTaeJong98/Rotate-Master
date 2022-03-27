package com.taetae98.rotatemaster.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.taetae98.rotatemaster.R
import com.taetae98.rotatemaster.protocol.NavigationScreen
import com.taetae98.rotatemaster.ui.compose.AppAdView
import com.taetae98.rotatemaster.viewModel.SettingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

object SettingScreen : NavigationScreen {
    override val route = "SettingScreen"

    @Composable
    override fun Compose(navController: NavHostController) {
        SettingScreen(navController = navController)
    }
}

@Composable
fun SettingScreen(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            Toolbar(navController)
        },
        bottomBar = {
            AppAdView(
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            )
        }
    ) {
        Content()
    }
}

@Composable
private fun Toolbar(navController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        },
        title = {
            Text(text = stringResource(id = R.string.setting))
        }
    )
}

@Preview
@Composable
private fun Content() {
    Column {
        NotificationItem()
        StartOnBoot()
    }
}

@Preview
@Composable
private fun NotificationItem(
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val state = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = "notification_state") {
        settingViewModel.settingRepository.getNotification().collect {
            state.value = it
            if (it) {
                settingViewModel.rotateNotificationManager.sendMessage()
            } else {
                settingViewModel.rotateNotificationManager.cancelMessage()
            }
        }
    }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                settingViewModel.viewModelScope.launch(Dispatchers.IO) {
                    settingViewModel.settingRepository.setNotification(!state.value)
                }
            }
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.notification))
            Switch(
                checked = state.value,
                onCheckedChange = null,
            )
        }
    }
}

@Preview
@Composable
private fun StartOnBoot(
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val state = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = "start_on_boot") {
        settingViewModel.settingRepository.getStartOnBoot().collect {
            state.value = it
        }
    }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                settingViewModel.viewModelScope.launch(Dispatchers.IO) {
                    settingViewModel.settingRepository.setStartOnBoot(!state.value)
                }
            }
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.start_on_boot))
            Switch(
                checked = state.value,
                onCheckedChange = null,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val navController = rememberNavController()
    SettingScreen(navController = navController)
}