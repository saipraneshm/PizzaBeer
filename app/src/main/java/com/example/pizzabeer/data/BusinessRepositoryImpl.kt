package com.example.pizzabeer.data

import com.example.pizzabeer.data.cloud.BusinessCloudStore
import com.example.pizzabeer.domain.BusinessRepository
import com.example.pizzabeer.domain.model.BusinessFilter
import com.example.pizzabeer.domain.model.BusinessesSearch
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class BusinessRepositoryImpl @Inject constructor(private val cloudStore: BusinessCloudStore) :
    BusinessRepository {

    override fun searchBusinesses(businessFilter: BusinessFilter): Flowable<BusinessesSearch> {
        return cloudStore.searchBusinesses(businessFilter)
    }
}