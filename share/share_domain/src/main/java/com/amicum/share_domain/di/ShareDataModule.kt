package com.amicum.share_domain.di

import android.content.Context
import com.amicum.share_domain.amicum_data.repository.AmicumRepository
import com.amicum.share_domain.metadata.repository.MetadataRepositoryImpl
import com.amicum.share_domain.metadata.repository.MetadataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShareDataModule {

    @Provides
    @Singleton
    fun provideAmicumRepository(
        @ApplicationContext context: Context
    ): AmicumRepository = AmicumRepository(context)

    @Provides
    @Singleton
    fun provideMetadataRepository(
        @ApplicationContext context: Context
    ): MetadataRepository = MetadataRepositoryImpl(context)
}