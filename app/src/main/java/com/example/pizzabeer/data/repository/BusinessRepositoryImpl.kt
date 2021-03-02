package com.example.pizzabeer.data.repository

import com.example.pizzabeer.data.repository.cloud.BusinessCloudStore
import com.example.pizzabeer.domain.repository.BusinessRepository
import com.example.pizzabeer.domain.model.BusinessFilter
import com.example.pizzabeer.domain.model.BusinessesSearch
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

/**
 * Repository implementation for Business, here we are injecting only cloudstore at the moment,
 * but it can be extended to inject dbStore and perform database operations as needed.
 *
 * Another approach would be to define a DataSource interface and implement related methods in
 * cloud and db store, which would then be injected into this class. It adds an overhead of adding
 * more code, so thought of just keeping it simple.
 */
class BusinessRepositoryImpl @Inject constructor(private val cloudStore: BusinessCloudStore) :
    BusinessRepository {

    override fun searchBusinesses(businessFilter: BusinessFilter): Flowable<BusinessesSearch> {
        return cloudStore.searchBusinesses(businessFilter)
    }
}