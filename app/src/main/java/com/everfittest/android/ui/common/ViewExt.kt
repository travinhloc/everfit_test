package com.everfittest.android.ui.common

import android.text.Editable
import android.text.Html
import android.text.Spanned
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.everfittest.android.R
import com.everfittest.android.model.SafeClickListener

fun TextView.setTextChangedListener(onTextChanged: () -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged()
        }

        override fun afterTextChanged(s: Editable?) {
        }

    })
}

fun TextView.setOnClickListener(onClick: () -> Unit) {
    setOnClickListener {
        onClick()
    }
}

fun TextView.setTextHtml(stringHtml:String){
    val s : Spanned = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(stringHtml, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(stringHtml)
    }
    text = s
}

fun View.setOnClickListenerAnimation(onClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        val animation = AnimationUtils.loadAnimation(context, R.anim.anim_icon_click)
        animation.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                onClick(it)
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        it.startAnimation(animation)
    }
    setOnClickListener(safeClickListener)
}

fun View.setOnSafeClickListener(onClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onClick(it)
    }
    setOnClickListener(safeClickListener)
}
