package com.example.pizzabeer.di

import com.example.pizzabeer.data.services.BusinessService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Provides
    fun providesBusinessService(retrofit: Retrofit) : BusinessService {
        return retrofit.create(BusinessService::class.java)
    }
}