package com.everfittest.android.ui.screens.assignment

import android.view.LayoutInflater
import android.view.ViewGroup
import co.lock.common.extensions.gone
import co.lock.common.extensions.visible
import com.everfittest.android.R
import com.everfittest.android.databinding.FragmentAssignmentBinding
import com.everfittest.android.databinding.ViewLoadingBinding
import com.everfittest.android.domain.model.DayOfWeek
import com.everfittest.android.extension.getResourceColor
import com.everfittest.android.extension.provideViewModels
import com.everfittest.android.model.common.LoadingStatus
import com.everfittest.android.model.common.LoadingType
import com.everfittest.android.ui.base.BaseFragment
import com.everfittest.android.ui.screens.MainNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AssignmentFragment : BaseFragment<FragmentAssignmentBinding>() {

    @Inject
    lateinit var navigator: MainNavigator

    private val viewModel: AssignmentViewModel by provideViewModels()
    private lateinit var viewLoadingBinding: ViewLoadingBinding
    private lateinit var datesOfWeekAdapter: DatesOfWeekAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAssignmentBinding
        get() = { inflater, container, attachToParent ->
            FragmentAssignmentBinding.inflate(inflater, container, attachToParent)
        }

    override fun setupView() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        with(binding){
            viewLoadingBinding = ViewLoadingBinding.bind(root)
            viewLoadingBinding.pbLoading.visible()
            srlList.setColorSchemeColors(requireContext().getResourceColor(R.color.blue))
        }
        setupDataList()
    }

    private fun setupDataList() {
        with(binding.rvData) {
            adapter = DatesOfWeekAdapter().also {
                datesOfWeekAdapter = it
            }
        }
        binding.srlList.setOnRefreshListener {
            viewModel.fetchData(LoadingType.REFRESH)
        }
    }

    override fun bindViewModel() {
        viewModel.error bindTo ::displayError
        viewModel.customLoading bindTo ::showLoading
        viewModel.input.datesOfWeek bindTo ::bindData
        viewModel.input.updateItem bindTo  ::updateItem
    }

    override fun bindViewEvents() {
        super.bindViewEvents()
        datesOfWeekAdapter.itemClick.bindTo {
            when(it){
                is DatesOfWeekAdapter.OnItemClick.Item -> {
                    viewModel.onUpdateAssignment(it.data)
                }
            }
        }
    }

    private fun updateItem(dateOfWeek: DayOfWeek) {
        if (datesOfWeekAdapter.items.isNotEmpty() && dateOfWeek.date != null) {
            datesOfWeekAdapter.updateItem(dateOfWeek)
        }
    }

    private fun bindData(dateOfWeeks: List<DayOfWeek>) {
        datesOfWeekAdapter.items = dateOfWeeks.toMutableList()
    }

    private fun showLoading(loadingStatus: LoadingStatus) {
        if (loadingStatus.isLoading) {
            when (loadingStatus.loadingType) {
                LoadingType.NORMAL -> {
                    viewLoadingBinding.pbLoading.visible()
                }
                else ->{}
            }
        } else {
            viewLoadingBinding.pbLoading.gone()
            binding.srlList.isRefreshing = false
        }
    }
}
