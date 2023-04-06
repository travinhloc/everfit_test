package com.everfittest.android.domain.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    var id: String = "",
    var assignments: List<Assignment> = arrayListOf(),
) : Parcelable
