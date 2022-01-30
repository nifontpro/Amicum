package com.amicum.report_presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.amicum.auth_domain.use_cases.AuthUseCases
import com.amicum.share_domain.amicum_data.use_cases.AmicumUseCases
import com.amicum.share_domain.metadata.use_cases.MetadataUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    metadataUseCases: MetadataUseCases,
    amicumUseCases: AmicumUseCases
) : ViewModel() {

    // Вошедший пользователь
    val authUser = authUseCases.getAuthUser()
//    val authUser = mutableStateOf(AuthUser("Test User"))

    // Признак видимости окна авторизации
    val isShowLoginDialog = authUseCases.getAuthState()
//    val isShowLoginDialog = mutableStateOf(false)

    // Смены
    val shifts = amicumUseCases.getShifts()

    val selectDate = mutableStateOf(LocalDate.now().asString())

    val message: SharedFlow<String> = metadataUseCases.subscribeMessage()

    fun logout() = authUseCases.logout()
//    fun logout() = {}
}