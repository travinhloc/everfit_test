package com.everfittest.android.extension

import android.text.format.DateUtils
import com.everfittest.android.domain.model.DayOfWeek
import com.everfittest.android.util.DateFormatPattern
import java.text.SimpleDateFormat
import java.util.*

fun Date.toDDString(): String {
    val output = SimpleDateFormat(DateFormatPattern.DD.pattern)
    return this.let { output.format(it) } ?: ""
}

fun Date.toEEEString(): String {
    val output = SimpleDateFormat(DateFormatPattern.EEE.pattern)
    return this.let { output.format(it) } ?: ""
}

fun Date.isPastDate(): Boolean {
    val selfCalendar = Calendar.getInstance().apply {
        time = this@isPastDate
    }
    val targetCalendar = Calendar.getInstance().apply {
        time = Date()
    }
    return targetCalendar.get(Calendar.YEAR) > selfCalendar.get(Calendar.YEAR) ||
            (targetCalendar.get(Calendar.YEAR) == selfCalendar.get(Calendar.YEAR)
                    && targetCalendar.get(Calendar.MONTH) > selfCalendar.get(Calendar.MONTH)) ||
            (targetCalendar.get(Calendar.YEAR) == selfCalendar.get(Calendar.YEAR) &&
                    targetCalendar.get(Calendar.MONTH) == selfCalendar.get(Calendar.MONTH) &&
                    targetCalendar.get(Calendar.DATE) > selfCalendar.get(Calendar.DATE))
}

fun Date.isToday(): Boolean = DateUtils.isToday(this.time)

fun getDaysOfWeek(): List<DayOfWeek> {
    val daysOfWeeks: MutableList<DayOfWeek> = arrayListOf()
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = Date()
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.getInstance().firstDayOfWeek)
    calendar.add(Calendar.DAY_OF_MONTH, 1)
    for (i in 1..Calendar.DAY_OF_WEEK) {
        val date = calendar.time
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        daysOfWeeks.add(DayOfWeek(date = date, isCurrentDay = date.isToday()))
    }
    return daysOfWeeks
}
