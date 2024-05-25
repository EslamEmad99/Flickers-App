package com.eslam.data.di

import com.eslam.data.data_source.local.LocalDataSource
import com.eslam.data.data_source.remote.MainDataSource
import com.eslam.data.repository.local.LocalRepositoryImpl
import com.eslam.data.repository.remote.MainRepositoryImpl
import com.eslam.domain.repository.local.LocalRepository
import com.eslam.domain.repository.remote.MainRepository
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
    fun providesLocalRepository(localDataSource: LocalDataSource): LocalRepository =
        LocalRepositoryImpl(localDataSource)

    @Singleton
    @Provides
    fun providesCommonRepository(mainDataSource: MainDataSource): MainRepository =
        MainRepositoryImpl(mainDataSource)
}