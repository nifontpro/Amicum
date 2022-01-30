package com.amicum.auth_domain.use_cases

import com.amicum.auth_domain.repository.AuthRepository
import com.example.models.Resource

class Login(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        login: String,
        password: String
    ): Resource<Unit> =
        authRepository.login(login, password)
}
