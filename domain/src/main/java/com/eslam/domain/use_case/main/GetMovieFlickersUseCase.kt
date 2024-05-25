package com.eslam.domain.use_case.main

import com.eslam.domain.repository.remote.MainRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * A use case represents a single action or task that the application can perform.
 * It encapsulates the business logic and rules necessary to execute a specific operation
 * in the application, such as retrieving data from a repository, processing it, and returning results.
 * Use cases promote a clean separation of concerns by keeping the domain logic separate from
 * the presentation layer, making the codebase more modular, reusable, and testable.
 * **/
class GetMovieFlickersUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(movieTitle: String) =
        flow { emitAll(mainRepository.getMovieFlickers(movieTitle = movieTitle)) }
}