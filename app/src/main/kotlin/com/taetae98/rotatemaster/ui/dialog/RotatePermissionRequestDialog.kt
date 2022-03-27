package com.taetae98.rotatemaster.ui.dialog

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.taetae98.rotatemaster.R

@Preview
@Composable
fun RotatePermissionRequestDialog() {
    AlertDialog(
        title = {
            Text(text = stringResource(id = R.string.request_permission))
        },
        text = {
            Text(text = stringResource(id = R.string.request_rotate_permission))
        },
        confirmButton = {
            ConfirmButton()
        },
        onDismissRequest = {

        }
    )
}

@Composable
private fun ConfirmButton() {
    val context = LocalContext.current

    TextButton(
        onClick = {
            startWriteSettingsActivity(context)
        }
    ) {
        Text(text = stringResource(id = R.string.confirm))
    }
}

private fun startWriteSettingsActivity(context: Context) {
    Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:${context.packageName}")).also {
        context.startActivity(it)
    }
}