package com.everfittest.android.domain.model

import android.os.Parcelable
import com.everfittest.android.domain.model.data.Data
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class DayOfWeek(
    var date: Date?,
    var data: Data = Data(),
    var isCurrentDay: Boolean = false,
) : Parcelable
