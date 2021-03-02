package com.example.pizzabeer.data.models

// This file will be used to put all the endpoints used in this app, along with the REST API verbs PREFIX

const val GET_BUSINESS_SEARCH = "v3/businesses/search"

// Having all the query constants at one place makes it easy to reference and reuse across multiple endpoints.
object QueryConstants {
    const val TERM = "term"
    const val LOCATION = "location"
    const val LATITUDE = "latitude"
    const val LONGITUDE = "longitude"
    const val LIMIT = "limit"
    const val OFFSET = "offset"
}