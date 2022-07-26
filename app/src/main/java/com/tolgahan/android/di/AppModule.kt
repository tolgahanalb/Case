package com.tolgahan.android.di

import com.tolgahan.android.core.config.Configuration
import com.tolgahan.android.core.config.BasicConfiguration
import com.tolgahan.android.domain.mapper.UserMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @AfterLogin
    @Provides
    fun provideGraphConfiguration(): Configuration {
        return BasicConfiguration()
    }

    @Provides
    @Singleton
    fun provideMovieMapper(): UserMapperImpl {
        return UserMapperImpl()
    }
}