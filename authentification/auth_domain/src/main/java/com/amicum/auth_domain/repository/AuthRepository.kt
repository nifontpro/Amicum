package com.amicum.auth_domain.repository

import com.amicum.auth_domain.model.AuthUser
import com.example.models.Resource
import kotlinx.coroutines.flow.StateFlow


interface AuthRepository {

    suspend fun login(login: String, password: String): Resource<Unit>

    fun logout()

    fun getAuthUser(): StateFlow<AuthUser>

    // Возвращает состояние регистрации
    fun getAuthState(): StateFlow<Boolean>
}