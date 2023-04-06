package com.everfittest.android.extension

inline fun <T> Iterable<T>.firstIndexOrNull(predicate: (T) -> Boolean): Int? {
    return this.mapIndexed { index, item -> Pair(index, item) }
        .firstOrNull { predicate(it.second) }
        ?.first
}

inline fun <T> Iterable<T>.lastIndexOrNull(predicate: (T) -> Boolean): Int? {
    return this.mapIndexed { index, item -> Pair(index, item) }
        .lastOrNull { predicate(it.second) }
        ?.first
}
