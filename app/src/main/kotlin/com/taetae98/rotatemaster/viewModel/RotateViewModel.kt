package com.taetae98.rotatemaster.viewModel

import androidx.lifecycle.ViewModel
import com.taetae98.rotatemaster.manager.RotateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RotateViewModel @Inject constructor(
    val rotateManager: RotateManager
) : ViewModel()