package com.everfittest.android.data.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.everfittest.android.data.database.entity.AssignmentEntity.Companion.ASSIGNMENT_ENTITY
import com.everfittest.android.data.database.entity.AssignmentEntity.Companion.ASSIGNMENT_ID
import com.everfittest.android.domain.model.data.Assignment
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = ASSIGNMENT_ENTITY, indices = [androidx.room.Index(
    value = [ASSIGNMENT_ID],
    unique = true
)])
data class AssignmentEntity(

    @PrimaryKey
    @ColumnInfo(name = ASSIGNMENT_ID)
    var id: String,

    @ColumnInfo(name = ASSIGNMENT_TITLE)
    var title: String,

    @ColumnInfo(name = ASSIGNMENT_STATUS)
    var status: Int,

    @ColumnInfo(name = ASSIGNMENT_EXERCISES_COUNT)
    var exercisesCount: Int,

    @ColumnInfo(name = ASSIGNMENT_SELECTED)
    var isSelected: Boolean,

) : Parcelable {

    companion object {
        const val ASSIGNMENT_ENTITY = "ASSIGNMENT_ENTITY"
        const val ASSIGNMENT_ID = "_id"
        const val ASSIGNMENT_TITLE = "title"
        const val ASSIGNMENT_STATUS = "status"
        const val ASSIGNMENT_EXERCISES_COUNT = "exercises_count"
        const val ASSIGNMENT_SELECTED = "selected"

        fun toAssignmentEntity(assignment: Assignment): AssignmentEntity {
            return AssignmentEntity(
                id = assignment.id,
                title = assignment.title.orEmpty(),
                status = assignment.status,
                exercisesCount = assignment.exercisesCount,
                isSelected = assignment.isSelected
            )
        }

        fun toAssignment(assignment: AssignmentEntity): Assignment {
            return Assignment(
                id = assignment.id,
                title = assignment.title,
                status = assignment.status,
                exercisesCount = assignment.exercisesCount,
                isSelected = assignment.isSelected
            )
        }
    }
}
