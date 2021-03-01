package com.example.pizzabeer.domain.usecase

import com.example.pizzabeer.domain.repository.BusinessRepository
import com.example.pizzabeer.domain.model.BusinessFilter
import com.example.pizzabeer.domain.model.BusinessesSearch
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class SearchBusinesses @Inject constructor(businessRepository: BusinessRepository) :
    BusinessRepository by businessRepository {

    operator fun invoke(businessFilter: BusinessFilter): Flowable<BusinessesSearch> {
        return searchBusinesses(businessFilter)
    }
}