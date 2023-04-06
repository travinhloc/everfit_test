package com.everfittest.android.ui

import android.content.Context
import com.everfittest.android.R
import com.everfittest.android.domain.data.error.AppError
import com.everfittest.android.domain.data.error.ValidateError

fun Throwable.userReadableMessage(context: Context): String {
    val customErrorMessage = when (this) {
        is ValidateError.NoConnectivityError -> context.getString(R.string.des_no_internet)
        is ValidateError.SomethingWrongError -> context.getString(R.string.error_something_wrong)
        else -> null
    }
    return customErrorMessage
        ?: (this as? AppError)?.readableMessage
        ?:(this as? Throwable)?.message
        ?: context.getString(R.string.error_generic)

}
