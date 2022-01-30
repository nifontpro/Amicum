package com.amicum.auth_presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amicum.auth_domain.use_cases.AuthUseCases
import com.amicum.share_domain.metadata.use_cases.MetadataUseCases
import com.example.models.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val metadataUseCases: MetadataUseCases
) : ViewModel() {

    val login = mutableStateOf("")
    val password = mutableStateOf("")
    val passwordVisibility = mutableStateOf(false)
    val authorizationOnActiveDirectory = mutableStateOf(false)

    val isLoginError = mutableStateOf<String?>(null)

    fun login() = viewModelScope.launch {
        when (val result = authUseCases.login(login.value, password.value)) {
            is Resource.Error -> {
                isLoginError.value = result.message
            }
            else -> {
                password.value = ""
                isLoginError.value = ""
                metadataUseCases.sendMessage("Авторизация выполнена успешно!")
            }
        }
    }
}