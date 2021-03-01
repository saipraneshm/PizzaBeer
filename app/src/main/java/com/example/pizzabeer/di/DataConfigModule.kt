package com.example.pizzabeer.di

import com.example.pizzabeer.domain.model.DataConfig
import com.example.pizzabeer.data.DataConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataConfigModule {

    @Singleton
    @Binds
    abstract fun bindsDataConfig(impl: DataConfigImpl): DataConfig
}