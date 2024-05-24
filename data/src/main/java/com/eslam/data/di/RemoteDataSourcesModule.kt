package com.eslam.data.di

import com.eslam.data.data_source.remote.CommonDataSource
import com.eslam.data.remote.data_source.CommonRemoteDataSourceImpl
import com.eslam.data.remote.end_points.CommonEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourcesModule {

    @Provides
    @Singleton
    fun provideCommonDataSource(commonEndPoints: CommonEndPoints): CommonDataSource =
        CommonRemoteDataSourceImpl(commonEndPoints)
}