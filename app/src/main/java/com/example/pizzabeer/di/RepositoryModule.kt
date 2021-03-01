package com.example.pizzabeer.di

import com.example.pizzabeer.data.BusinessRepositoryImpl
import com.example.pizzabeer.domain.BusinessRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsBusinessRepository(impl: BusinessRepositoryImpl): BusinessRepository
}