package com.example.pizzabeer.ui.home

import androidx.lifecycle.ViewModel
import com.example.pizzabeer.domain.model.BusinessFilter
import com.example.pizzabeer.domain.usecase.SearchBusinesses
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val searchBusinesses: SearchBusinesses) :
    ViewModel() {

    fun searchBusinesses() {
        searchBusinesses(
            BusinessFilter(
                term = "pizza",
                location = "111 Sutter St #1300, San Francisco, CA 94104"
            )
        )
            .onErrorResumeNext {
                Timber.e(it)
                Flowable.empty()
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}