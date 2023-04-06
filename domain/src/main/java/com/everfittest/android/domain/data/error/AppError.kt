package com.everfittest.android.domain.data.error

open class AppError(
    override val cause: Throwable?
) : Throwable(cause) {

    val readableMessage: String?
        get() = (cause as? JsonApiException)?.error?.message
}

class Ignored(cause: Exception?) : AppError(cause)

class NoConnectivityError(cause: Exception?) : AppError(cause)
