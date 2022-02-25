package com.taetae98.rotatemaster.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.taetae98.rotatemaster.R
import com.taetae98.rotatemaster.protocol.NavigationScreen
import com.taetae98.rotatemaster.protocol.RotateItem
import com.taetae98.rotatemaster.ui.theme.RotateMasterTheme

object MainScreen : NavigationScreen {
    override val route = "MainScreen"

    @Composable
    override fun Compose(navController: NavHostController) {
        MainScreen(navController = navController)
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Toolbar(navController = navController)
        }
    ) {
        Content(navController = navController)
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
        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(navController: NavHostController) {
    val items = listOf(
        RotateItem.Portrait {

        },
        RotateItem.Landscape {

        },
        RotateItem.ReversePortrait {

        },
        RotateItem.ReverseLandscape {

        },
        RotateItem.RotateLeft {

        },
        RotateItem.RotateRight {

        }
    )

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(items) { item ->
            RotateItem(item = item)
        }
    }
}
@Composable
private fun SettingAction(navController: NavHostController) {
    IconButton(
        onClick = {

        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_round_settings_24),
            contentDescription = stringResource(id = R.string.setting),
            tint = Color.White
        )
    }
}

@Composable
private fun RotateItem(item: RotateItem) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = item.icon),
                contentDescription = stringResource(id = item.contentDescription),
                colorFilter = ColorFilter.tint(Color.Black)
            )

            Text(
                modifier = Modifier.padding(10.dp),
                text = stringResource(id = item.description),
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    RotateMasterTheme {
        MainScreen(navController = rememberNavController())
    }
}