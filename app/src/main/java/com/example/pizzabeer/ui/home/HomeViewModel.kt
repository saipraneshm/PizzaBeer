package com.example.pizzabeer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pizzabeer.domain.model.Business
import com.example.pizzabeer.domain.model.BusinessFilter
import com.example.pizzabeer.domain.model.Location
import com.example.pizzabeer.domain.usecase.SearchBusinesses
import com.example.pizzabeer.ui.BaseViewModel
import com.example.pizzabeer.ui.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val searchBusinesses: SearchBusinesses) :
    BaseViewModel() {

    private val _location = MutableLiveData<Location>()
    val locationLiveData: LiveData<Location>
        get() = _location

    private val _businesses = MutableLiveData<NetworkResult<List<Business>>>()
    val businessesLiveData: LiveData<NetworkResult<List<Business>>>
        get() = _businesses

    init {
        fetchData()
    }

    override fun fetchData() {
        // Setting the Turo's office location as default location.
        _location.value = Location(
            address1 = "111 Sutter St #1300",
            city = "San Francisco",
            state = "California",
            zip_code = "94104"
        )

        // loading the initial list with pizza
        _location.value?.let {
            searchBusinesses(Terms.PIZZA.displayValue, it)
        }
    }

    // searching businesses using the term and the location.
    // TODO: Can add support for pagination here, need to store the offset and limit in viewModel.
    fun searchBusinesses(term: String, location: Location) {
        val d = searchBusinesses(
            BusinessFilter(
                term = term,
                location = location.fullAddress
            )
        )
            .doOnSubscribe {
                _businesses.postValue(Loading)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { _businesses.value = OK(it.businesses) },
                onError = { _businesses.value = NetworkError(it) }
            )

        compositeDisposable.add(d)
    }
}