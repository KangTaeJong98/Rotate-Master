package com.taetae98.rotatemaster.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.taetae98.rotatemaster.repository.SettingRepository
import com.taetae98.rotatemaster.repository.SettingRepository.SettingDataStore.Companion.SettingDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
class DataStoreModule {
    @Provides
    @SettingRepository.SettingDataStore
    fun providesSettingDataStore(
        @ApplicationContext
        context: Context
    ): DataStore<Preferences> {
        return context.SettingDataStore
    }
}