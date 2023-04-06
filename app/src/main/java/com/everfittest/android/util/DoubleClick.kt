package com.everfittest.android.util

import android.os.Handler
import android.os.Looper
import android.view.View

class DoubleClick(
    private val doubleClickListener: DoubleClickListener,
    private var DOUBLE_CLICK_INTERVAL: Long = 200L
) : View.OnClickListener {

    private val mHandler = Handler(Looper.getMainLooper())

    private var clicks = 0

    private var isBusy = false

    override fun onClick(view: View) {
        if (!isBusy) {
            isBusy = true
            clicks++
            mHandler.postDelayed({
                if (clicks >= 2) {  // Double tap.
                    doubleClickListener.onDoubleClick(view)
                }
                if (clicks == 1) {  // Single tap
                    doubleClickListener.onSingleClick(view)
                }
                clicks = 0
            }, DOUBLE_CLICK_INTERVAL)
            isBusy = false
        }
    }
}

interface DoubleClickListener {
    fun onSingleClick(view: View?)
    fun onDoubleClick(view: View?)
}
