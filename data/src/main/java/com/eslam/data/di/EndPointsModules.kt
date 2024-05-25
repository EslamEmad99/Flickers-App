package com.eslam.data.di

import com.eslam.data.remote.end_points.MainEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object EndPointsModules {

    @Singleton
    @Provides
    fun providesCommonEndPoints(retrofit: Retrofit): MainEndPoints =
        retrofit.create(MainEndPoints::class.java)
}