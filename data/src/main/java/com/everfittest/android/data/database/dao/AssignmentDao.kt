package com.everfittest.android.data.database.dao

import androidx.room.*
import com.everfittest.android.data.database.entity.AssignmentEntity
import com.everfittest.android.data.database.entity.AssignmentEntity.Companion.ASSIGNMENT_ENTITY
import com.everfittest.android.data.database.entity.AssignmentEntity.Companion.ASSIGNMENT_ID

@Dao
interface AssignmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignment(assignment: AssignmentEntity)

    @Delete
    suspend fun deleteAssignment(assignment: AssignmentEntity)

    @Query("SELECT * FROM $ASSIGNMENT_ENTITY WHERE $ASSIGNMENT_ID = :id")
    fun getAssignmentsById(id: String): AssignmentEntity

}