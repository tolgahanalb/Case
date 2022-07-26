package com.tolgahan.android.di

import com.tolgahan.android.data.repository.UserRepositoryImpl
import com.tolgahan.android.data.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun moviesRepository(mainRepositoryImpl: UserRepositoryImpl): UserRepository
}