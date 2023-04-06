package com.everfittest.android.domain.usecase

import com.everfittest.android.domain.model.data.Assignment
import com.everfittest.android.domain.repository.DaoRepository
import com.everfittest.android.domain.usecase.utils.DataBaseCaseResult
import javax.inject.Inject

class DaoUseCase @Inject constructor(private val daoUseCase: DaoRepository) {

    suspend fun executeInsertAssignment(assignment: Assignment): DataBaseCaseResult<Boolean> {
        return try {
            daoUseCase.insertAssignment(assignment)
            DataBaseCaseResult.Success(true)
        } catch (e: Exception){
            DataBaseCaseResult.Error(Throwable("Inserted Error!"))
        }
    }

    suspend fun executeDeleteAssignment(assignment: Assignment): DataBaseCaseResult<Boolean> {
        return try {
            daoUseCase.deleteAssignment(assignment)
            DataBaseCaseResult.Success(true)
        } catch (e: Exception){
            DataBaseCaseResult.Error(Throwable("Deleted Error!"))
        }
    }

    suspend fun executeGetAssignmentById(id: String): DataBaseCaseResult<Assignment> {
        return try {
            DataBaseCaseResult.Success(daoUseCase.getAssignmentsById(id))
        } catch (e: Exception){
            DataBaseCaseResult.Error(Throwable("Get data Error!"))
        }
    }

}
