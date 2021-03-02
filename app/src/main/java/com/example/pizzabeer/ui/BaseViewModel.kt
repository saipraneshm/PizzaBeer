package com.example.pizzabeer.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    abstract fun fetchData()

    override fun onCleared() {
        compositeDisposable.dispose() // this takes care of disposing of the stream once view model is destroyed.
        super.onCleared()
    }
}