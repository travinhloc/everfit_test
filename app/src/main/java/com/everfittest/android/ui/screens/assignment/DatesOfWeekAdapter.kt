package com.everfittest.android.ui.screens.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.lock.common.extensions.invisible
import co.lock.common.extensions.visible
import com.everfittest.android.databinding.ItemDateOfWeekBinding
import com.everfittest.android.domain.model.DayOfWeek
import com.everfittest.android.extension.toDDString
import com.everfittest.android.extension.toEEEString
import com.everfittest.android.model.ItemClickable
import com.everfittest.android.model.ItemClickableImpl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class DatesOfWeekAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    ItemClickable<DatesOfWeekAdapter.OnItemClick> by ItemClickableImpl() {

    var items = mutableListOf<DayOfWeek>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ViewHolderItem(ItemDateOfWeekBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DatesOfWeekAdapter.ViewHolderItem -> {
                holder.bind(items[position])
            }
        }
    }

    private fun getItem(position: Int): DayOfWeek  = items[position]

    fun updateItem(dateOfWeek: DayOfWeek) {
        items.forEachIndexed { index, date ->
            dateOfWeek.date?.let {
                if (it.compareTo(date.date!!) == 0) {
                    items[index] = dateOfWeek
                    notifyItemChanged(index)
                }
            }
        }
    }

    internal inner class ViewHolderItem(
        private val binding: ItemDateOfWeekBinding,
    ) : RecyclerView.ViewHolder(binding.root), LayoutContainer {
        override val containerView: View get() = itemView

        fun bind(data: DayOfWeek?) = data?.run {
            with(binding) {
                val context = root.context
                dateOfWeek = this@run
                data.date?.let{
                    tvDayOfWeek.text = it.toEEEString()
                    tvDate.text = it.toDDString()
                }
                val assignments = data.data.assignments
                var postCommentAdapter: AssignmentsAdapter?
                val layoutManagerChild = LinearLayoutManager(context)
                layoutManagerChild.initialPrefetchItemCount = assignments.size
                val childListener : AssignmentsAdapter.Listener =
                    object : AssignmentsAdapter.Listener {
                        override fun onSelected(dateOfWeek: DayOfWeek) {
                            CoroutineScope(Dispatchers.IO).launch {
                                notifyItemClick(OnItemClick.Item(dateOfWeek))
                            }
                        }
                    }
                if (assignments.isNotEmpty()) {
                    rvAssignment.visible()
                    val viewPool = RecyclerView.RecycledViewPool()

                    with(rvAssignment) {
                        adapter = AssignmentsAdapter(data, childListener).also {
                            postCommentAdapter = it
                        }
                        layoutManager = layoutManagerChild
                    }
                    postCommentAdapter?.let {
                        it.items.clear()
                        it.items.addAll(assignments)
                        it.notifyDataSetChanged()
                    }
                    rvAssignment.setRecycledViewPool(viewPool)
                } else {
                    with(rvAssignment) {
                        adapter = AssignmentsAdapter(data, childListener).also {
                            postCommentAdapter = it
                        }
                        layoutManager = layoutManagerChild
                    }
                    rvAssignment.invisible()
                }
            }
        }
    }

    sealed class OnItemClick {
        data class Item(val data: DayOfWeek) : OnItemClick()
    }
}
