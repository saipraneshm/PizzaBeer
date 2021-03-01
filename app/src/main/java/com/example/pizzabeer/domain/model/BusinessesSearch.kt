package com.example.pizzabeer.domain.model

data class BusinessesSearch(
    val businesses: List<Business>,
    val region: Region,
    val total: Int
)