package com.eslam.data.di

import com.eslam.data.data_source.remote.CommonDataSource
import com.eslam.data.repository.remote.CommonRepositoryImpl
import com.eslam.domain.repository.remote.CommonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Singleton
    @Provides
    fun providesCommonRepository(commonDataSource: CommonDataSource): CommonRepository =
        CommonRepositoryImpl(commonDataSource)
}