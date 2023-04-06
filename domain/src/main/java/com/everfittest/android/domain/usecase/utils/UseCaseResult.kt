package com.everfittest.android.domain.usecase.utils

import com.everfittest.android.domain.data.error.ErrorsResponse

sealed class UseCaseResult<out T : Any?> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    object NetworkError: UseCaseResult<Nothing>()
    data class GenericError(val code: Int? = null, val error: ErrorsResponse): UseCaseResult<Nothing>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
}
