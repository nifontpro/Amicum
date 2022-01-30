package com.amicum.share_data.di

import android.app.Application
import androidx.room.Room
import com.amicum.share_data.local.AmicumDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbDataModule {

    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application): AmicumDatabase {
        return Room.databaseBuilder(
            app,
            AmicumDatabase::class.java,
            "amicum_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

/*    @Provides
    @Singleton
    fun provideNetworkStatus(
        @ApplicationContext context: Context
    ): Boolean = NetworkStatus(context)*/
}
