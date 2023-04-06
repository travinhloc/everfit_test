package com.everfittest.android.extension

import android.text.*
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import co.lock.common.extensions.invisible
import co.lock.common.extensions.visible
import co.lock.common.extensions.visibleOrGone
import com.everfittest.android.R
import com.everfittest.android.domain.model.DayOfWeek
import com.everfittest.android.domain.model.data.Assignment
import com.google.android.material.card.MaterialCardView
import java.util.*

@BindingAdapter("layout_width")
fun View.setLayoutWidth(width: Float) {
    val layoutParams = this.layoutParams
    layoutParams.width = width.toInt()
    this.layoutParams = layoutParams
}

@BindingAdapter("isSelect")
fun View.setSelect(selected: Boolean) {
    isSelected = selected
}

@BindingAdapter("isEnable")
fun View.setEnable(selected: Boolean) {
    isEnabled = selected
}


@BindingAdapter("enableExercirse")
fun View.setEnableExercirse(dateOfWeek: DayOfWeek) {
    dateOfWeek.date?.let {
        isEnabled = it <= Date()
    }
}

@BindingAdapter(value = ["dateOfWeek", "assignment"])
fun MaterialCardView.setCardBackground(dateOfWeek: DayOfWeek, assignment: Assignment) {
    dateOfWeek.date?.let {
        val color = if (it > Date()) {
            R.color.gray
        } else {
            if (assignment.isSelected) R.color.textSelected else R.color.gray
        }
        this.setCardBackgroundColor(context.getResourceColor(color))
    }
}

@BindingAdapter(value = ["dateOfWeekOfText", "assignmentOfText"])
fun TextView.setTitleColor(dateOfWeek: DayOfWeek, assignment: Assignment) {
    dateOfWeek.date?.let {
        if (it > Date()) {
            setTextColor(context.getResourceColor(R.color.gray_1))
        } else if (assignment.isSelected) {
            setTextColor(context.getResourceColor(R.color.white))
        } else {
            setTextColor(context.getResourceColor(R.color.text_dark1))
        }
    }
}

@BindingAdapter(value = ["dateOfWeekOfSubText", "assignmentOfSubText"])
fun TextView.setSubText(dateOfWeek: DayOfWeek, assignment: Assignment) {
    dateOfWeek.date?.let {
        text = if (assignment.status > 0) {
            context.getString(R.string.completed)
        } else {
            val exercises = if (it.isPastDate()) R.string.exercises_missed else R.string.exercises
            context.getString(exercises, assignment.exercisesCount)
        }
        this.setTitleColor(dateOfWeek, assignment)
    }
}
@BindingAdapter(value = ["dateOfWeekOfMissedText", "assignmentOfMissedText"])
fun TextView.setMissedText(dateOfWeek: DayOfWeek, assignment: Assignment) {
    dateOfWeek.date?.let {
        visibleOrGone(!(assignment.status > 0 || !it.isPastDate()))
    }
}

@BindingAdapter("checkEmptyItem")
fun View.checkItemEmpty(assignment: Assignment){
    if (assignment.title.isNullOrEmpty() && assignment.exercisesCount <= 0) invisible() else visible()
}