package com.eslam.data.local

import android.content.Context
import com.eslam.data.data_source.local.LocalDataSource
import com.eslam.domain.model.MoviesData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : LocalDataSource {
    override suspend fun getMovies(): MoviesData = withContext(Dispatchers.IO) {
        val jsonString = context.assets.open("movies.json").bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val movieListType = object : TypeToken<MoviesData>() {}.type
        gson.fromJson(jsonString, movieListType)
    }
}