package com.amicum.auth_domain.use_cases

data class AuthUseCases(
    val login: Login,
    val logout: Logout,
    val getAuthState: GetAuthState,
    val getAuthUser: GetAuthUser
)