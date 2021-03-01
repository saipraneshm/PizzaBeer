package com.example.pizzabeer.domain.repository

import com.example.pizzabeer.domain.model.BusinessFilter
import com.example.pizzabeer.domain.model.BusinessesSearch
import io.reactivex.rxjava3.core.Flowable

interface BusinessRepository {

    fun searchBusinesses(businessFilter: BusinessFilter): Flowable<BusinessesSearch>
}