package com.everfittest.android.util

import java.util.TimeZone

enum class DateFormatPattern(val pattern: String) {
    ISO8601("yyyy-MM-dd'T'HH:mm:ss"),
    DD("dd"),
    EEE("EEE"),
}

enum class AppTimeZone(val timeZone: TimeZone) {
    UTC(TimeZone.getTimeZone("UTC")),
}