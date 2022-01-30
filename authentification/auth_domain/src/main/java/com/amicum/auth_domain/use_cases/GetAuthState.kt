package com.amicum.auth_domain.use_cases

import com.amicum.auth_domain.repository.AuthRepository
import kotlinx.coroutines.flow.StateFlow

class GetAuthState(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): StateFlow<Boolean> = authRepository.getAuthState()
}
