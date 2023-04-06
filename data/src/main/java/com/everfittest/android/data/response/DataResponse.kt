package com.everfittest.android.data.response

import com.everfittest.android.domain.model.data.Assignment
import com.everfittest.android.domain.model.data.Data
import com.squareup.moshi.Json

data class DataResponse(
    @Json(name = "data") val dataResponse: List<DataDetailsResponse>?,
){
    fun toDataList():List<Data> {
        return dataResponse?.map { it.toDataDetails() }?: arrayListOf()
    }
}

data class DataDetailsResponse(
    @Json(name = "_id") val id: String?,
    @Json(name = "assignments") val assignments: List<AssignmentResponse>?,
) {
    fun toDataDetails(): Data {
        return Data(
            id = this.id.orEmpty(),
            assignments = assignments?.map { it.toAssignment() }?: arrayListOf()
        )
    }
}

data class AssignmentResponse(
    @Json(name = "_id") val id: String?,
    @Json(name = "status") val status: Int?,
    @Json(name = "title") val title: String?,
    @Json(name = "exercises_count") val exercisesCount: Int?
    ){

    fun toAssignment(): Assignment {
        return Assignment(
            id = this.id.orEmpty(),
            status = this.status ?: 0,
            title = this.title.orEmpty(),
            exercisesCount = this.exercisesCount ?: 0
        )
    }
}
