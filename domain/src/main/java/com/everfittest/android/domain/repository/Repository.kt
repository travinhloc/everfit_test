package com.everfittest.android.domain.repository

import com.everfittest.android.domain.model.data.Data

interface Repository {
    suspend fun getAssignments(): List<Data>
}
