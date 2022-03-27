package com.taetae98.rotatemaster.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingRepository @Inject constructor(
    @SettingDataStore
    private val settingDataStore: DataStore<Preferences>
) {
    suspend fun setNotification(boolean: Boolean) {
        settingDataStore.edit {
            it[NOTIFICATION] = boolean
        }
    }

    suspend fun setStartOnBoot(boolean: Boolean) {
        settingDataStore.edit {
            it[START_ON_BOOT] = boolean
        }
    }

    fun getNotification(): Flow<Boolean> {
        return settingDataStore.data.map {
            it[NOTIFICATION] ?: false
        }
    }

    fun getStartOnBoot(): Flow<Boolean> {
        return settingDataStore.data.map {
            it[START_ON_BOOT] ?: false
        }
    }

    companion object {
        private val NOTIFICATION by lazy { booleanPreferencesKey("NOTIFICATION") }
        private val START_ON_BOOT by lazy { booleanPreferencesKey("START_ON_BOOT") }
    }

    annotation class SettingDataStore {
        companion object {
            val Context.SettingDataStore by preferencesDataStore(name = "SettingDataStore")
        }
    }
}