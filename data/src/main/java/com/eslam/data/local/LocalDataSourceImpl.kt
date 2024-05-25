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

/**
 * A data source is needed to abstract the source of data from the domain and presentation layers.
 * It allows the application to fetch data from various sources such as a local database, network,
 * or remote API without tightly coupling these implementations to the domain logic.
 * By separating the data source from the domain logic, we achieve better modularity and
 * maintainability, making it easier to switch or extend data sources in the future.
 * **/
class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : LocalDataSource {

    // movies.json is a JSON file containing a list of movies.
    // We use Gson to parse the JSON data into a MoviesData object to be used in the application.
    override suspend fun getMovies(): MoviesData = withContext(Dispatchers.IO) {
        val jsonString = context.assets.open("movies.json").bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val movieListType = object : TypeToken<MoviesData>() {}.type
        gson.fromJson(jsonString, movieListType)
    }
}