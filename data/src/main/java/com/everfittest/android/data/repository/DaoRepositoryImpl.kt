package com.everfittest.android.data.repository

import com.everfittest.android.data.database.EverfitTestDatabase
import com.everfittest.android.data.database.entity.AssignmentEntity.Companion.toAssignment
import com.everfittest.android.data.database.entity.AssignmentEntity.Companion.toAssignmentEntity
import com.everfittest.android.domain.model.data.Assignment
import com.everfittest.android.domain.repository.DaoRepository

class DaoRepositoryImpl(private val database: EverfitTestDatabase) :
    DaoRepository {
    override suspend fun insertAssignment(assignment: Assignment) {
        database.assignmentDao().insertAssignment(toAssignmentEntity(assignment))
    }

    override suspend fun deleteAssignment(assignment: Assignment){
        database.assignmentDao().deleteAssignment(toAssignmentEntity(assignment))
    }

    override suspend fun getAssignmentsById(id: String): Assignment {
        return toAssignment(database.assignmentDao().getAssignmentsById(id))
    }
}
