package com.everfittest.android.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.everfittest.android.model.common.AlertDialogMessageData
import com.everfittest.android.ui.common.AlertDialogFragment
import com.everfittest.android.ui.common.MessageProgressDialogFragment

inline fun FragmentManager.inTransaction(
    allowStateLoss: Boolean = false,
    func: FragmentTransaction.() -> FragmentTransaction
) {
    beginTransaction().apply {
        func(this)
        when {
            !isStateSaved -> {
                commit()
            }
            allowStateLoss -> {
                commitAllowingStateLoss()
            }
        }
    }
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, addToStack: Boolean = true) {
    supportFragmentManager.inTransaction {
        if (addToStack) {
            add(frameId, fragment, fragment.javaClass.simpleName).addToBackStack(fragment.javaClass.simpleName)
        } else {
            add(frameId, fragment, fragment.javaClass.simpleName)
        }
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, addToStack: Boolean = false) {
    supportFragmentManager.inTransaction {
        if (addToStack) replace(frameId, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
        else
            replace(frameId, fragment, fragment.javaClass.simpleName)
    }
}

fun FragmentActivity.showHideProgressDialog(isLoading: Boolean, message: String = "") {
    if (isLoading) {
        showMessageProgressDialog(message)
    } else {
        hideMessageProgressDialog()
    }
}

fun FragmentActivity.showMessageProgressDialog(message: String = "") {
    (supportFragmentManager.findFragmentByTag("messageProgress") as? MessageProgressDialogFragment)?.update(
        message
    ) ?: kotlin.run {
        MessageProgressDialogFragment.getInstance(message)
            .show(supportFragmentManager, "messageProgress")
    }
}

fun FragmentActivity.hideMessageProgressDialog() {
    (supportFragmentManager.findFragmentByTag("messageProgress") as? MessageProgressDialogFragment)?.also {
        it.dismiss()
    }
}

fun FragmentActivity.showAlertDialogFragment(alertDialogMessageData: AlertDialogMessageData) {
    if (supportFragmentManager.findFragmentByTag(null) != null) {
        return
    }
    AlertDialogFragment.getInstance(alertDialogMessageData).also {
        it.show(supportFragmentManager, null)
    }
}
