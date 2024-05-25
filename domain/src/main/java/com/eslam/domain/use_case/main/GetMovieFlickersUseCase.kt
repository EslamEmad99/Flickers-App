package com.eslam.domain.use_case.main

import com.eslam.domain.repository.remote.MainRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieFlickersUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(movieTitle: String) =
        flow { emitAll(mainRepository.getMovieFlickers(movieTitle = movieTitle)) }
}