package com.everfittest.android.ui.common

import android.util.TypedValue
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private fun BottomSheetDialogFragment.appAreaViewHeight(): Int =
    requireActivity().window.decorView.findViewById<View>(android.R.id.content).height

private fun BottomSheetDialogFragment.actionBarHeight(): Int {
    val t = TypedValue()
    requireActivity().theme.resolveAttribute(android.R.attr.actionBarSize, t, true)
    return TypedValue.complexToDimensionPixelSize(t.data, resources.displayMetrics)
}

private fun BottomSheetDialogFragment.maxHeight(): Int =
    appAreaViewHeight() - actionBarHeight()

fun BottomSheetDialogFragment.applyMaxHeight(): Int = maxHeight().also { height ->
    (dialog as BottomSheetDialog).behavior.apply {
        peekHeight = height
    }
}
fun BottomSheetDialogFragment.applyFullHeight(): Int = appAreaViewHeight().also { height ->
    (dialog as BottomSheetDialog).behavior.apply {
        peekHeight = height
    }
}
