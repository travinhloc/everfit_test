package com.everfittest.android.extension

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.everfittest.android.model.common.AlertDialogMessageData
import com.everfittest.android.ui.common.AlertDialogFragment
import com.everfittest.android.ui.common.MessageProgressDialogFragment

fun DialogFragment.showAllowingStateLoss(fm: FragmentManager, tag: String) {
    fm.beginTransaction()
        .add(this, tag)
        .commitAllowingStateLoss()
}

fun Fragment.showAlertDialogFragment(alertDialogMessageData: AlertDialogMessageData) {
    if (childFragmentManager.findFragmentByTag(null) != null) {
        return
    }
    AlertDialogFragment.getInstance(alertDialogMessageData).also {
        it.show(childFragmentManager, null)
    }
}

fun <T> Fragment.setResult(key: String, resultKey: String, value: T) {
    parentFragmentManager.setFragmentResult(
        key,
        bundleOf(resultKey to value)
    )
}


fun <T> Fragment.getResult(key: String): T? {
    return findNavController().currentBackStackEntry?.savedStateHandle?.get(key)
}

fun Fragment.addFragment(fragment: Fragment, frameId: Int, addToStack: Boolean = true) {
    childFragmentManager.inTransaction {
        if (addToStack) {
            add(frameId, fragment, fragment.javaClass.simpleName).addToBackStack(fragment.javaClass.simpleName)
        } else {
            add(frameId, fragment, fragment.javaClass.simpleName)
        }
    }
}

fun Fragment.replaceFragment(
    @IdRes containerId: Int, fragment: Fragment,
    addToBackStack: Boolean = false, tag: String = fragment::class.java.simpleName,
) {
    childFragmentManager.beginTransaction().apply {
        if (addToBackStack) {
            addToBackStack(tag)
        }
        replace(containerId, fragment, tag).commit()
    }
}

fun Fragment.showHideProgressDialog(isLoading: Boolean, message: String = "") {
    if (isLoading) {
        showMessageProgressDialog(message)
    } else {
        hideMessageProgressDialog()
    }
}

fun Fragment.showMessageProgressDialog(message: String = "") {
    (childFragmentManager.findFragmentByTag("messageProgress") as? MessageProgressDialogFragment)?.update(
        message
    ) ?: kotlin.run {
        MessageProgressDialogFragment.getInstance(message)
            .show(childFragmentManager, "messageProgress")
    }
}

fun Fragment.hideMessageProgressDialog() {
    (childFragmentManager.findFragmentByTag("messageProgress") as? MessageProgressDialogFragment)?.also {
        it.dismiss()
    }
}

