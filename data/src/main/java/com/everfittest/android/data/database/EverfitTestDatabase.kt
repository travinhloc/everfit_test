package com.everfittest.android.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.everfittest.android.data.database.dao.AssignmentDao
import com.everfittest.android.data.database.entity.AssignmentEntity
import id.zelory.compressor.BuildConfig

@Database(
    entities = [AssignmentEntity::class], version = 3
)
abstract class EverfitTestDatabase : RoomDatabase() {

    abstract fun assignmentDao(): AssignmentDao

    companion object {
        private var DB_NAME = "EVERFIT_TEST_" + BuildConfig.BUILD_TYPE

        @Volatile
        private var INSTANCE: EverfitTestDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context): EverfitTestDatabase =
            Room.databaseBuilder(context, EverfitTestDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}