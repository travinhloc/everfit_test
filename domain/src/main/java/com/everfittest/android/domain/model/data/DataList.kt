package com.everfittest.android.domain.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataList(
    var colorsList: List<Data> = arrayListOf(),
) : Parcelable
