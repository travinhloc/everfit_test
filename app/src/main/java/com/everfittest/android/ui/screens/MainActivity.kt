package com.everfittest.android.ui.screens

import android.view.LayoutInflater
import com.everfittest.android.databinding.ActivityMainBinding
import com.everfittest.android.extension.provideViewModels
import com.everfittest.android.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = { inflater -> ActivityMainBinding.inflate(inflater) }
    override val viewModel: MainViewModel by provideViewModels()
}
