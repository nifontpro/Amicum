package com.amicum.share_domain.di

import com.amicum.share_domain.amicum_data.repository.AmicumRepository
import com.amicum.share_domain.amicum_data.use_cases.AmicumUseCases
import com.amicum.share_domain.amicum_data.use_cases.GetShifts
import com.amicum.share_domain.amicum_data.use_cases.SetShifts
import com.amicum.share_domain.metadata.repository.MetadataRepository
import com.amicum.share_domain.metadata.use_cases.MetadataUseCases
import com.amicum.share_domain.metadata.use_cases.SendMessage
import com.amicum.share_domain.metadata.use_cases.SubscribeMessage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ShareDomainModule {

    @Provides
    fun provideAmicumUseCases(
        amicumRepository: AmicumRepository
    ): AmicumUseCases = AmicumUseCases(
        setShifts = SetShifts(repository = amicumRepository),
        getShifts = GetShifts(repository = amicumRepository)
    )

    @Provides
    fun provideShareUseCases(
        metadataRepository: MetadataRepository
    ): MetadataUseCases = MetadataUseCases(
        sendMessage = SendMessage(metadataRepository),
        subscribeMessage = SubscribeMessage(metadataRepository)
    )
}