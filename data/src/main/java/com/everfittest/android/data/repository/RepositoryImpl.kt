package com.everfittest.android.data.repository

import com.everfittest.android.data.service.ApiService
import com.everfittest.android.domain.model.data.Data
import com.everfittest.android.domain.repository.Repository

class RepositoryImpl(private val apiService: ApiService) : Repository {
    override suspend fun getAssignments(): List<Data> =
        apiService.getAssignments().toDataList()

}
