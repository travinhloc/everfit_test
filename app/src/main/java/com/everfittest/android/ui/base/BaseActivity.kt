package com.everfittest.android.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.everfittest.android.ui.common.Toaster
import com.everfittest.android.ui.userReadableMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), BaseActivityCallbacks {
    @Inject
    lateinit var toaster: Toaster

    protected abstract val viewModel: BaseViewModel

    protected abstract val bindingInflater: (LayoutInflater) -> VB

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingInflater.invoke(layoutInflater).apply {
            _binding = this
        }.root)
        (this as? BaseActivityCallbacks)?.let {
            initViewModel()
            setupView()
            bindViewEvents()
            bindViewModel()
        }
    }

    override fun initViewModel() {}

    override fun setupView() {}

    override fun bindViewEvents() {}

    override fun bindViewModel() {}

    open fun displayError(error: String) {
        toaster.display(error)
    }

    open fun displayError(error: Throwable) {
        val message = error.userReadableMessage(this)
        toaster.display(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    protected inline infix fun <T> Flow<T>.bindTo(crossinline action: (T) -> Unit) {
        with(this) {
            lifecycleScope.launch {
                repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
                    collect { action(it) }
                }
            }
        }
    }

    protected inline infix fun <T> Flow<T>.bindNotRepeat(crossinline action: (T) -> Unit) {
        with(this) {
            lifecycleScope.launch {
                collect { action(it) }
            }
        }
    }
}
