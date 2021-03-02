package com.example.pizzabeer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pizzabeer.domain.model.Business
import com.example.pizzabeer.domain.model.BusinessFilter
import com.example.pizzabeer.domain.model.Location
import com.example.pizzabeer.domain.usecase.SearchBusinesses
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val searchBusinesses: SearchBusinesses) :
    ViewModel() {

    private val _location = MutableLiveData<Location>()
    val locationLiveData: LiveData<Location>
        get() = _location

    private val _businesses = MutableLiveData<List<Business>>()
    val businessesLiveData: LiveData<List<Business>>
        get() = _businesses

    private val disposable = CompositeDisposable()

    init {
        fetchData()
    }

    private fun fetchData() {
        _location.value = Location(
            address1 = "111 Sutter St #1300",
            city = "San Francisco",
            state = "California",
            zip_code = "94104"
        )

        _location.value?.let {
            searchBusinesses("pizza", it)
        }
    }

    fun searchBusinesses(term: String, location: Location) {
        val d = searchBusinesses(
            BusinessFilter(
                term = term,
                location = location.fullAddress
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                _businesses.value = it.businesses
            }
            .onErrorResumeNext {
                Timber.e(it)
                Flowable.empty()
            }
            .subscribe()

        disposable.add(d)
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}