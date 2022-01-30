package com.amicum.report_presentation.test_work

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestWorkViewModel @Inject constructor(): ViewModel() {

    val comment = mutableStateOf("")
}