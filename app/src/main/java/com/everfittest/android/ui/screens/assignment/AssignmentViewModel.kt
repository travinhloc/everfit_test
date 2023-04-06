package com.everfittest.android.ui.screens.assignment

import com.everfittest.android.domain.data.error.ValidateError
import com.everfittest.android.domain.model.DayOfWeek
import com.everfittest.android.domain.usecase.AssignmentUseCase
import com.everfittest.android.domain.usecase.DaoUseCase
import com.everfittest.android.domain.usecase.utils.DataBaseCaseResult
import com.everfittest.android.domain.usecase.utils.UseCaseResult
import com.everfittest.android.extension.getDaysOfWeek
import com.everfittest.android.model.common.LoadingStatus
import com.everfittest.android.model.common.LoadingType
import com.everfittest.android.ui.base.BaseViewModel
import com.everfittest.android.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

interface Input{
    val datesOfWeek: StateFlow<List<DayOfWeek>>
    val updateItem: SharedFlow<DayOfWeek>
}
@HiltViewModel
class AssignmentViewModel @Inject constructor(
    private val assignmentUseCase: AssignmentUseCase,
    private val daoUseCase: DaoUseCase,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers), Input {

    val input: Input = this

    private val _datesOfWeek = MutableStateFlow<List<DayOfWeek>>(listOf())
    override val datesOfWeek: StateFlow<List<DayOfWeek>>
        get() = _datesOfWeek

    private val _updateItem = MutableSharedFlow<DayOfWeek>()
    override val updateItem: SharedFlow<DayOfWeek>
        get() = _updateItem

    init {
        _datesOfWeek.value = getDaysOfWeek()
        fetchData()
    }

    fun fetchData(loadingType: LoadingType = LoadingType.NORMAL) {
        execute {
            _customLoading.emit(LoadingStatus(true, loadingType))
            if (loadingType == LoadingType.REFRESH) {
                _datesOfWeek.value = getDaysOfWeek()
            }
            when (val result = assignmentUseCase.executeGetAssignments()) {
                is UseCaseResult.Success -> {
                        if (result.data.isNotEmpty()) {
                            for(i in 0 .. _datesOfWeek.value.size){
                                if (i < result.data.size){
                                    _datesOfWeek.value[i].apply {
                                        data = result.data[i]
                                        data.assignments.forEach {
                                            when(val localAssignment = daoUseCase.executeGetAssignmentById(it.id)){
                                                is DataBaseCaseResult.Success -> it.isSelected = it.id == localAssignment.data.id
                                                else -> {}
                                            }
                                        }
                                        _updateItem.emit(this)
                                    }
                                }
                            }
                        }
                }
                is UseCaseResult.GenericError -> _error.emit(Throwable(result.error.message))
                is UseCaseResult.NetworkError -> _error.emit(ValidateError.NoConnectivityError())
                is UseCaseResult.Error -> _error.emit(ValidateError.SomethingWrongError())
            }
            _customLoading.emit(LoadingStatus(false))
        }
    }

    fun onUpdateAssignment(dayOfWeek: DayOfWeek) {
        execute {
            _datesOfWeek.value.forEachIndexed { index, day ->
                if (dayOfWeek.data.id == day.data.id) {
                    _datesOfWeek.value[index].data = dayOfWeek.data
                    _updateItem.emit(_datesOfWeek.value[index])
                    dayOfWeek.data.assignments.forEach { assignment ->
                        if (assignment.isSelected) {
                            daoUseCase.executeInsertAssignment(assignment)
                        } else {
                            daoUseCase.executeDeleteAssignment(assignment)
                        }
                    }
                    return@forEachIndexed
                }
            }
        }
    }
}
