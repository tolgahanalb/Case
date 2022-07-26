package com.tolgahan.android.di

import android.content.Context
import androidx.room.Room
import com.tolgahan.android.core.constants.AppConstants
import com.tolgahan.android.data.db.DbManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DbManager {
        return Room.databaseBuilder(
            context.applicationContext,
            DbManager::class.java,
            AppConstants.DbName
        ).build()
    }
}
