package com.example.pizzabeer.data

import com.example.pizzabeer.domain.model.DataConfig
import javax.inject.Inject

class DataConfigImpl @Inject constructor() : DataConfig {
    override fun getApiKey(): String {
        return "2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx"
    }

    override fun getBaseUrl(): String {
        return "https://api.yelp.com/"
    }
}