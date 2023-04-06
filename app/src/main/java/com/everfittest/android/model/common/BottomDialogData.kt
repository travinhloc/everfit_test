package com.everfittest.android.model.common

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.everfittest.android.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class BottomDialogData(
    val items: MutableList<Item> = mutableListOf(),
    val isShowChecked: Boolean = false,
    val isCancellable: Boolean = true,
    @StringRes val headerResId: Int? = null,
    val headerString: String? = null,
    var isHideItemLine: Boolean = false,
    ) : Parcelable {
    @Parcelize
    data class Item(
        @StringRes val titleResId: Int? = null,
        val titleString: String? = null,
        @StringRes val messageResId: Int? = null,
        @ColorRes val titleColorId: Int? = R.color.text_black,
        val messageString: String? = null,
        var isSelected: Boolean = false,
    ) : Parcelable
}
