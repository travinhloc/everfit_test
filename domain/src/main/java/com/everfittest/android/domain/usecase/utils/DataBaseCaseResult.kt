package com.everfittest.android.domain.usecase.utils

sealed class DataBaseCaseResult<out T : Any?> {
    class Success<out T : Any>(val data: T) : DataBaseCaseResult<T>()
    class Error(val exception: Throwable) : DataBaseCaseResult<Nothing>()
}
