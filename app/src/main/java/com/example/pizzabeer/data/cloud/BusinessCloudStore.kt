package com.example.pizzabeer.data.cloud

import com.example.pizzabeer.data.services.BusinessService
import com.example.pizzabeer.domain.model.BusinessFilter
import com.example.pizzabeer.domain.model.BusinessesSearch
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class BusinessCloudStore @Inject constructor() {

    @Inject
    lateinit var businessService: BusinessService

    fun searchBusinesses(businessFilter: BusinessFilter): Flowable<BusinessesSearch> {
        return with(businessFilter) {
            businessService.searchBusinesses(term, location, latitude, longitude, limit, offset)
        }
    }
}