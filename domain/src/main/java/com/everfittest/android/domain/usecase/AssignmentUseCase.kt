package com.everfittest.android.domain.usecase

import com.everfittest.android.domain.model.data.Data
import com.everfittest.android.domain.repository.Repository
import com.everfittest.android.domain.usecase.utils.UseCaseResult
import com.everfittest.android.domain.usecase.utils.convertErrorBody
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class AssignmentUseCase @Inject constructor(private val repository: Repository) {

    suspend fun executeGetAssignments(): UseCaseResult<List<Data>> {
        return try {
            UseCaseResult.Success(repository.getAssignments())
        } catch (e: Exception) {
            e.printStackTrace()
            when(e){
                is UnknownHostException-> UseCaseResult.NetworkError
                is ConnectException -> UseCaseResult.NetworkError
                is HttpException -> UseCaseResult.GenericError(e.code(), convertErrorBody(e)!!)
                else -> UseCaseResult.Error(e)
            }
        }
    }
}
