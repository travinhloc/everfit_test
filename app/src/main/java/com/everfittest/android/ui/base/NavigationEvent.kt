package com.everfittest.android.ui.base

sealed class NavigationEvent {

    object PopBackStack : NavigationEvent()
}
