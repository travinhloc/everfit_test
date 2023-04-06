package com.everfittest.android.ui.screens.assignment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.everfittest.android.databinding.ItemAssignmentBinding
import com.everfittest.android.domain.model.DayOfWeek
import com.everfittest.android.domain.model.data.Assignment
import com.everfittest.android.ui.common.setOnSafeClickListener
import kotlinx.android.extensions.LayoutContainer

@SuppressLint("NotifyDataSetChanged")
internal class AssignmentsAdapter(val dateOfWeek: DayOfWeek, val listener: Listener?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListOf<Assignment>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolderItem(ItemAssignmentBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AssignmentsAdapter.ViewHolderItem -> {
                holder.bind(items[position])
            }
        }
    }
    internal inner class ViewHolderItem(
        private val binding: ItemAssignmentBinding,
    ) : RecyclerView.ViewHolder(binding.root), LayoutContainer {
        override val containerView: View get() = itemView

        fun bind(model: Assignment?) = model?.run {
            with(binding) {
                assignment = this@run
                dateOfWeek = this@AssignmentsAdapter.dateOfWeek
                rootView.setOnSafeClickListener {
                    this@AssignmentsAdapter.dateOfWeek.apply {
                        data.assignments.forEach { ass ->
                            ass.isSelected = ass.id == model.id && !ass.isSelected
                        }
                    }
                    listener?.onSelected(this@AssignmentsAdapter.dateOfWeek)
                }
            }
        }
    }

    interface Listener {
        fun onSelected(dateOfWeek: DayOfWeek)
    }
}
