package com.taetae98.rotatemaster.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.taetae98.rotatemaster.repository.SettingRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {
    @Inject
    lateinit var settingRepository: SettingRepository

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            Intent.ACTION_BOOT_COMPLETED -> onBootCompleted()
        }
    }

    private fun onBootCompleted() {
        CoroutineScope(Dispatchers.IO).launch {
            if (isNotificationAvailable()) {

            }
        }
    }

    private suspend fun isNotificationAvailable(): Boolean {
        return  settingRepository.getNotification().first() &&
                settingRepository.getStartOnBoot().first()
    }
}