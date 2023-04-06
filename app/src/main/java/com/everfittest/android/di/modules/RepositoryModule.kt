package com.everfittest.android.di.modules

import com.everfittest.android.data.database.EverfitTestDatabase
import com.everfittest.android.data.repository.DaoRepositoryImpl
import com.everfittest.android.data.repository.RepositoryImpl
import com.everfittest.android.data.service.ApiService
import com.everfittest.android.domain.repository.DaoRepository
import com.everfittest.android.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(apiService: ApiService): Repository = RepositoryImpl(apiService)

    @Provides
    fun provideDaoRepository(database: EverfitTestDatabase): DaoRepository = DaoRepositoryImpl(database)
}
