package com.example.pizzabeer.domain.model

data class Location(
    val address1: String? = "",
    val address2: String? = "",
    val address3: String? = "",
    val city: String? = "",
    val country: String? = "",
    val state: String? = "",
    val zip_code: String? = ""
) {

    val shortAddress: String
        get() = "$address1, $city, $country"

    val fullAddress: String
        get() {
            return with(StringBuilder()) {
                fun String?.appendNonNull(appendComma: Boolean = true) {
                    if (!isNullOrEmpty()) {
                        append(this)
                        if (appendComma) append(", ")
                    }
                }
                address1.appendNonNull()
                address2.appendNonNull()
                address3.appendNonNull()
                city.appendNonNull()
                country.appendNonNull()
                state.appendNonNull()
                zip_code.appendNonNull(false)
                toString()
            }

        }
}