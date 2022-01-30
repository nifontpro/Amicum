package com.amicum.auth_domain.use_cases

import com.amicum.auth_domain.repository.AuthRepository

class GetAuthUser(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.getAuthUser()
}
