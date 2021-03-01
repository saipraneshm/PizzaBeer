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

/**
 * Responsible for providing networking (retrofit) dependencies
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun providesInterceptors(dataConfig: DataConfig): List<Interceptor> {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        // Adding a headerInterceptor which takes care of adding the authorization header.
        val headerInterceptor = Interceptor { chain ->
            val request = chain.request()

            val authorizedRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer ${dataConfig.getApiKey()}")

            chain.proceed(authorizedRequest.build())
        }

        return mutableListOf<Interceptor>().apply {
            add(logger)
            add(headerInterceptor)
        }
    }

    @Provides
    fun providesOkHttpClient(interceptors: List<Interceptor>): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        for (interceptor in interceptors) {
            okHttpClientBuilder.addInterceptor(interceptor)
        }

        return okHttpClientBuilder.build()
    }

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