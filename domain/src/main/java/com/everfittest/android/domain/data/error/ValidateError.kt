package com.everfittest.android.domain.data.error

sealed class ValidateError(
    override val cause: Throwable?
) : AppError(cause) {
    class NoConnectivityError : ValidateError(Throwable())
    class SomethingWrongError : ValidateError(Throwable())
}
