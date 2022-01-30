package com.amicum.share_domain.amicum_data.repository

import android.content.Context
import com.amicum.share_domain.amicum_data.model.Shift
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Модуль общих данных, составляющих предметную область приложения
 * Здесь будут хранится детали отчета, смена, подразделение и т. д.
 */

class AmicumRepository(
    private val context: Context
) {
    private val _shifts = MutableStateFlow<List<Shift>>(emptyList())
    val shifts = _shifts.asStateFlow()

    fun setShiftList(shiftList: List<Shift>) {
        _shifts.value = shiftList
    }
}