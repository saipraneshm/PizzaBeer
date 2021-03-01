package com.example.pizzabeer.domain.model

interface DataConfig {

    fun getApiKey(): String

    fun getBaseUrl(): String
}