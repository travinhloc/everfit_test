package com.everfittest.android.model.common

import androidx.annotation.StringRes
import java.io.Serializable

data class AlertDialogMessageData(
    @StringRes val titleResId: Int? = null,
    val titleString: String? = null,
    @StringRes val messageResId: Int? = null,
    val messageString: String? = null,
    @StringRes val positiveTextRestId: Int? = null,
    @StringRes val negativeTextResId: Int? = null,
    @StringRes val neutralTextRestId: Int? = null,
    val isCancellable: Boolean = false,
    val tag: String? = null,
) : Serializable
