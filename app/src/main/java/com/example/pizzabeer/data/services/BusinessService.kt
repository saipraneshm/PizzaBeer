package com.example.pizzabeer.data.services

import com.example.pizzabeer.domain.model.BusinessesSearch
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface BusinessService {

    @GET("v3/businesses/search")
    fun searchBusinesses(
        @Query("term") term: String,
        @Query("location") location: String?,
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): Flowable<BusinessesSearch>
}