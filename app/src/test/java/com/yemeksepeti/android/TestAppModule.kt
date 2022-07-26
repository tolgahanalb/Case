package com.tolgahan.android

import android.content.Context
import androidx.room.Room
import com.tolgahan.android.data.db.DbManager
import com.tolgahan.android.di.AppModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named

@Module
@InstallIn(AppModule::class)
object TestAppModule {

    @Provides
    @Named("testDatabase")
    fun injectInMemoryRoom(@ApplicationContext context : Context) =
        Room.inMemoryDatabaseBuilder(context,DbManager::class.java)
            .allowMainThreadQueries()
            .build()
}