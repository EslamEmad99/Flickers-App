package com.eslam.domain.use_case.common

import com.eslam.domain.repository.remote.CommonRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val commonRepository: CommonRepository
) {

    suspend operator fun invoke() = flow { emitAll(commonRepository.countries()) }
}