package com.everfittest.android.di.modules

import android.content.Context
import com.everfittest.android.data.database.EverfitTestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    companion object {
        @Provides
        fun provideRoomDatabase(@ApplicationContext context: Context): EverfitTestDatabase = EverfitTestDatabase.invoke(context)
    }
}
