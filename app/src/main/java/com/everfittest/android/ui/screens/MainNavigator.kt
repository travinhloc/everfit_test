package com.everfittest.android.ui.screens

import androidx.fragment.app.Fragment
import com.everfittest.android.R
import com.everfittest.android.ui.base.BaseNavigator
import com.everfittest.android.ui.base.BaseNavigatorImpl
import com.everfittest.android.ui.base.NavigationEvent
import javax.inject.Inject

interface MainNavigator : BaseNavigator

class MainNavigatorImpl @Inject constructor(
    fragment: Fragment,
) : BaseNavigatorImpl(fragment), MainNavigator {

    override val navHostFragmentId = R.id.navHostFragment

    override fun navigate(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.PopBackStack -> popBack()
        }
    }

}
