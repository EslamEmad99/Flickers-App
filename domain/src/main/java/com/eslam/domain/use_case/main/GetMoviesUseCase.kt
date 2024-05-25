package com.eslam.domain.use_case.main

import com.eslam.domain.model.YearMovies
import com.eslam.domain.repository.local.LocalRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(searchQuery: String?) = flow {
        val movies = localRepository.getMovies()
        val filteredMovies = if (searchQuery.isNullOrBlank()) {
            movies
        } else {
            movies.filter { it.title.contains(searchQuery, ignoreCase = true) }
        }
        val groupedMovies = filteredMovies.groupBy { it.year }
        val yearGroups = groupedMovies.map { entry ->
            val topMovies = entry.value.sortedByDescending { it.rating }.take(5)
            YearMovies(entry.key, topMovies)
        }.sortedByDescending { it.year }
        emit(yearGroups)
    }
}