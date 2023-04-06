package com.everfittest.android.domain.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Assignment(
    var id: String ="",
    var status: Int = 0,
    var title: String? = "",
    var exercisesCount: Int = 0,
    var isSelected: Boolean = false
) : Parcelable