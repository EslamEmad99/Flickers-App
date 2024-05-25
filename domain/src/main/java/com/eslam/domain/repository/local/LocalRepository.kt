package com.eslam.domain.repository.local

import com.eslam.domain.model.Movie

interface LocalRepository {

    suspend fun getMovies(): List<Movie>
}