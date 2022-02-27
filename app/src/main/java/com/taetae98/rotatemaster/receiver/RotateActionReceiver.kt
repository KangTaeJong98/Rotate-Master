package com.taetae98.rotatemaster.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.taetae98.rotatemaster.application.MainActivity
import com.taetae98.rotatemaster.manager.RotateManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RotateActionReceiver : BroadcastReceiver() {
    @Inject
    lateinit var rotateManager: RotateManager

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            CONTENT_ACTION -> {
                onContent(context)
            }
            ROTATE_ACTION -> {
                onRotate()
            }
            ROTATE_LOCK_ACTION -> {
                onRotateLock()
            }
            PORTRAIT_ACTION -> {
                onPortrait()
            }
            LANDSCAPE_ACTION -> {
                onLandscape()
            }
            REVERSE_PORTRAIT_ACTION -> {
                onReversePortrait()
            }
            REVERSE_LANDSCAPE_ACTION -> {
                onReverseLandscape()
            }
            ROTATE_LEFT_ACTION -> {
                onRotateLeft()
            }
            ROTATE_RIGHT_ACTION -> {
                onRotateRight()
            }
        }
    }

    private fun onContent(context: Context) {
        context.startActivity(
            Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )
    }

    private fun onRotate() {
        rotateManager.setRotationState(RotateManager.AUTO_ROTATION)
    }

    private fun onRotateLock() {
        rotateManager.setRotationState(RotateManager.LOCK_ROTATION)
    }

    private fun onPortrait() {
        rotateManager.setRotation(RotateManager.PORTRAIT)
    }

    private fun onLandscape() {
        rotateManager.setRotation(RotateManager.LANDSCAPE)
    }

    private fun onReversePortrait() {
        rotateManager.setRotation(RotateManager.REVERSE_PORTRAIT)
    }

    private fun onReverseLandscape() {
        rotateManager.setRotation(RotateManager.REVERSE_LANDSCAPE)
    }

    private fun onRotateLeft() {
        rotateManager.rotateLeft()
    }

    private fun onRotateRight() {
        rotateManager.rotateRight()
    }

    companion object {
        const val CONTENT_ACTION = "RotateActionReceiver.CONTENT_ACTION"
        const val ROTATE_ACTION = "RotateActionReceiver.ROTATE_ACTION"
        const val ROTATE_LOCK_ACTION = "RotateActionReceiver.ROTATE_LOCK_ACTION"
        const val PORTRAIT_ACTION = "RotateActionReceiver.PORTRAIT_ACTION"
        const val LANDSCAPE_ACTION = "RotateActionReceiver.LANDSCAPE_ACTION"
        const val REVERSE_PORTRAIT_ACTION = "RotateActionReceiver.REVERSE_PORTRAIT_ACTION"
        const val REVERSE_LANDSCAPE_ACTION = "RotateActionReceiver.REVERSE_LANDSCAPE_ACTION"
        const val ROTATE_LEFT_ACTION = "RotateActionReceiver.ROTATE_LEFT_ACTION"
        const val ROTATE_RIGHT_ACTION = "RotateActionReceiver.ROTATE_RIGHT_ACTION"
    }
}