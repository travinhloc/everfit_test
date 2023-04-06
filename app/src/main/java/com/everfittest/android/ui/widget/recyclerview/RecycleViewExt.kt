package com.everfittest.android.ui.widget.recyclerview

import android.animation.ObjectAnimator
import android.view.View
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.scrollToBottom() {
    if ((adapter?.itemCount?: 0) <= 0) return
    val lastItemPosition = adapter?.itemCount!! - 1
    val linearLayoutManager = layoutManager as LinearLayoutManager?
    linearLayoutManager?.let { ll->
        ll.scrollToPositionWithOffset(lastItemPosition, 0)
        post {
            val target = linearLayoutManager.findViewByPosition(lastItemPosition)
            if (target != null) {
                val offset = measuredHeight - target.measuredHeight
                ll.scrollToPositionWithOffset(lastItemPosition, offset)
            }
        }
    }
}

fun RecyclerView.scrollToEnd() {
    if ((adapter?.itemCount ?: 0) <= 0) return
    (adapter!!.itemCount - 1).takeIf { it > 0 }?.let(this::scrollToPosition)
}

fun RecyclerView.smoothScrollToEnd() {
    if ((adapter?.itemCount ?: 0) <= 0) return
    (adapter!!.itemCount - 1).takeIf { it > 0 }?.let(this::smoothScrollToPosition)
}


fun translationObjectY(
    targetView: View?,
    startY: Float,
    endY: Float,
    duration: Long = 200L
) : ObjectAnimator {
    return ObjectAnimator.ofFloat(targetView, "translationY", startY, endY).apply {
        this.duration = duration
        interpolator = LinearOutSlowInInterpolator()
        start()
    }
}