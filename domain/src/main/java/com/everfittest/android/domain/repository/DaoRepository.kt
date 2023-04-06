package com.everfittest.android.domain.repository

import com.everfittest.android.domain.model.data.Assignment

interface DaoRepository {
    suspend fun insertAssignment(assignment: Assignment)
    suspend fun deleteAssignment(assignment: Assignment)
    suspend fun getAssignmentsById(id: String): Assignment
}