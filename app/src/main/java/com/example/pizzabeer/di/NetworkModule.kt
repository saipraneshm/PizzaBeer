package com.example.pizzabeer.di

import com.example.pizzabeer.domain.model.DataConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Responsible for providing networking (retrofit) dependencies
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(dataConfig: DataConfig): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        // Adding a headerInterceptor which takes care of adding the authorization header.
        val headerInterceptor = Interceptor { chain ->
            val request = chain.request()

            val authorizedRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer ${dataConfig.getApiKey()}")

            chain.proceed(authorizedRequest.build())
        }

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, dataConfig: DataConfig): Retrofit {
        return Retrofit.Builder()
            .baseUrl(dataConfig.getBaseUrl())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}