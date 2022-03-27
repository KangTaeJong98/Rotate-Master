package com.taetae98.rotatemaster.manager

import android.content.Context
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RotateManager @Inject constructor(
    @ApplicationContext
    private val context: Context,
) {
    private fun getRotation(): Int {
        return Settings.System.getInt(context.contentResolver, Settings.System.USER_ROTATION, PORTRAIT)
    }

    private fun getLeftRotation(rotation: Int): Int {
        return (rotation + 1) % 4
    }

    private fun getRightRotation(rotation: Int): Int {
        return (4 + (rotation - 1)) % 4
    }

    fun isAvailable(): Boolean {
        return Settings.System.canWrite(context)
    }

    fun setRotationState(state: Int) {
        Settings.System.putInt(context.contentResolver, Settings.System.ACCELEROMETER_ROTATION, state)
    }

    fun setRotation(rotation: Int) {
        Settings.System.putInt(context.contentResolver, Settings.System.USER_ROTATION, rotation)
        setRotationState(LOCK_ROTATION)
    }

    fun rotateLeft() {
        setRotation(getLeftRotation(getRotation()))
    }

    fun rotateRight() {
        setRotation(getRightRotation(getRotation()))
    }

    companion object {
        const val LOCK_ROTATION = 0
        const val AUTO_ROTATION = 1

        const val PORTRAIT = 0
        const val LANDSCAPE = 1
        const val REVERSE_PORTRAIT = 2
        const val REVERSE_LANDSCAPE = 3
    }
}