package com.example.pizzabeer.domain.usecase

import com.example.pizzabeer.domain.model.BusinessFilter
import com.example.pizzabeer.domain.model.BusinessesSearch
import com.example.pizzabeer.domain.repository.BusinessRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

/**
 * All the business logic related to searching of businesses goes here, and we then inject this
 * usecase into the viewModel, keeping the viewModel cleaner.
 *
 * Here we are throwing an error if either the location or the combination of latitude and longitude
 * is not sent as part of the filter, as those are the required parameters for the API.
 */
class SearchBusinesses @Inject constructor(businessRepository: BusinessRepository) :
    BusinessRepository by businessRepository {

    operator fun invoke(businessFilter: BusinessFilter): Flowable<BusinessesSearch> {
        return Completable.fromCallable {
            if (businessFilter.location != null
                || (businessFilter.latitude != null && businessFilter.longitude != null)
            ) {
                return@fromCallable ""
            } else {
                throw Throwable("Please send either location or latitude and longitude as part of the filter.")
            }
        }.andThen(searchBusinesses(businessFilter))
    }
}