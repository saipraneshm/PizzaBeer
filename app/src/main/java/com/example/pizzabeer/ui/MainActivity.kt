package com.example.pizzabeer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzabeer.R
import com.example.pizzabeer.domain.model.BusinessFilter
import com.example.pizzabeer.domain.usecase.SearchBusinesses
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}