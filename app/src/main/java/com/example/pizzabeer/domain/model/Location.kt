package com.example.pizzabeer.domain.model

data class Location(
    val address1: String,
    val address2: String = "",
    val address3: String = "",
    val city: String = "",
    val country: String = "",
    val state: String = "",
    val zip_code: String = ""
) {

    val shortAddress: String
        get() = "$address1, $city, $country"

    val fullAddress: String
        get() {
            return with(StringBuilder()) {
                if (address1.isNotEmpty()) {
                    append(address1)
                    append(", ")
                }
                if (address2.isNotEmpty()) {
                    append(address2)
                    append(", ")
                }
                if (address3.isNotEmpty()) {
                    append(address3)
                    append(", ")
                }
                if (city.isNotEmpty()) {
                    append(city)
                    append(", ")
                }
                if (country.isNotEmpty()) {
                    append(country)
                    append(", ")
                }
                if (state.isNotEmpty()) {
                    append(state)
                    append(", ")
                }
                if (zip_code.isNotEmpty()) {
                    append(zip_code)
                }

                this.toString()
            }

        }
}