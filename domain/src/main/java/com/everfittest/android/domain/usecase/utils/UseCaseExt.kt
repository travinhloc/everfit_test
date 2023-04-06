package com.everfittest.android.domain.usecase.utils

import com.everfittest.android.domain.data.error.ErrorsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.HttpException
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

fun convertErrorBody(throwable: HttpException): ErrorsResponse? {
    val errorResult = StringBuilder()
    val moshiAdapter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build().adapter(ErrorsResponse::class.java)
    val i: InputStream = throwable.response()!!.errorBody()!!.byteStream()
    val r = BufferedReader(InputStreamReader(i))
    var line: String?
    return try {
        while (r.readLine().also { line = it } != null) {
            errorResult.append(line).append('\n')
        }
        moshiAdapter.fromJson(errorResult.toString())
    } catch (exception: Exception) {
        try {
         val s = "{\"message\":[$errorResult"
            moshiAdapter.fromJson(s)
        } catch (e:Exception){
            ErrorsResponse()
        }
    }
}