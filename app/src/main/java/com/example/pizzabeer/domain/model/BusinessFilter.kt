package com.example.pizzabeer.domain.model

data class BusinessFilter(
    var term: String,
    var location: String,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var limit: Int? = null,
    var offset: Int? = null
)