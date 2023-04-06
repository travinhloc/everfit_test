package com.everfittest.android.ui.screens

import com.everfittest.android.ui.base.BaseViewModel
import com.everfittest.android.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers)
