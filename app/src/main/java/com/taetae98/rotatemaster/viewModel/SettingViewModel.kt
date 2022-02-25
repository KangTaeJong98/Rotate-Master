package com.taetae98.rotatemaster.viewModel

import androidx.lifecycle.ViewModel
import com.taetae98.rotatemaster.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    val settingRepository: SettingRepository
) : ViewModel()