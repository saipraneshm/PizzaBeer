package com.example.pizzabeer.data.services

import com.example.pizzabeer.data.models.GET_BUSINESS_SEARCH
import com.example.pizzabeer.data.models.QueryConstants.LATITUDE
import com.example.pizzabeer.data.models.QueryConstants.LIMIT
import com.example.pizzabeer.data.models.QueryConstants.LOCATION
import com.example.pizzabeer.data.models.QueryConstants.LONGITUDE
import com.example.pizzabeer.data.models.QueryConstants.OFFSET
import com.example.pizzabeer.data.models.QueryConstants.TERM
import com.example.pizzabeer.domain.model.BusinessesSearch
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface BusinessService {

    @GET(GET_BUSINESS_SEARCH)
    fun searchBusinesses(
        @Query(TERM) term: String,
        @Query(LOCATION) location: String?,
        @Query(LATITUDE) latitude: Double?,
        @Query(LONGITUDE) longitude: Double?,
        @Query(LIMIT) limit: Int?,
        @Query(OFFSET) offset: Int?
    ): Flowable<BusinessesSearch>
}