package com.everfittest.android.util


fun formatPhoneNumber(input: String): String? {
    val prefix = if(input.contains("+")) "" else "+"
    val output: String = when (input.length) {
        7,8,9 -> String.format("$prefix%s %s", input.substring(0, 3), input.substring(3, input.length))
        10 -> String.format(
            "$prefix%s %s %s",
            input.substring(0, 3),
            input.substring(3, 5),
            input.substring(5, input.length),
        )
        11,12,13,14 -> String.format(
            "$prefix%s %s %s %s",
            input.substring(0, 3),
            input.substring(3, 5),
            input.substring(5, 9),
            input.substring(9, input.length)
        )
        else -> input
    }
    return output
}

