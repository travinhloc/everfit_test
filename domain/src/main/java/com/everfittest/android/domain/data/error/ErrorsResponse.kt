package com.everfittest.android.domain.data.error

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorsResponse(
    @Json(name = "status") val status: Boolean = false,
    @Json(name = "messages") val message: String? = "Something went wrong. Please try again later!",
    @Json(name = "errors")  val errors: Errors? = Errors()
)

@JsonClass(generateAdapter = true)
data class Errors(
    @Json(name = "email") val email: List<String>? = arrayListOf(),
    @Json(name = "phone") val phone: List<String>? = arrayListOf(),
    @Json(name = "password") val password: List<String>? = arrayListOf(),
){
    constructor(): this(arrayListOf(), arrayListOf())
}