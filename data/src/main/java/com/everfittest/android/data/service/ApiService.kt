package com.everfittest.android.data.service

import com.everfittest.android.data.response.DataResponse
import retrofit2.http.*

interface ApiService {
    @GET("workouts")
    suspend fun getAssignments(): DataResponse

}
