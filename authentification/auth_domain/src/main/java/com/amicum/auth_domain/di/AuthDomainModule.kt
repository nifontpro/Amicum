package com.amicum.auth_domain.di

import com.amicum.auth_domain.repository.AuthRepository
import com.amicum.auth_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthDomainModule {

    @ViewModelScoped
    @Provides
    fun provideAuthUseCases(
        authRepository: AuthRepository
    ): AuthUseCases = AuthUseCases(
        login = Login(authRepository),
        logout = Logout(authRepository),
        getAuthState = GetAuthState(authRepository),
        getAuthUser = GetAuthUser(authRepository)
    )
}