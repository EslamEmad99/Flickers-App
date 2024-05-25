package com.eslam.data.di

import android.content.Context
import com.eslam.data.data_source.local.LocalDataSource
import com.eslam.data.data_source.remote.MainDataSource
import com.eslam.data.local.LocalDataSourceImpl
import com.eslam.data.remote.data_source.MainRemoteDataSourceImpl
import com.eslam.data.remote.end_points.MainEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(context: Context): LocalDataSource =
        LocalDataSourceImpl(context)

    @Provides
    @Singleton
    fun provideCommonDataSource(mainEndPoints: MainEndPoints): MainDataSource =
        MainRemoteDataSourceImpl(mainEndPoints)
}