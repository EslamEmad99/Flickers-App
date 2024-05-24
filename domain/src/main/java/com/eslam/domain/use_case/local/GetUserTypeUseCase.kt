package com.eslam.domain.use_case.local

import com.eslam.domain.repository.local.PreferenceRepository
import javax.inject.Inject

class GetUserTypeUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {

    suspend operator fun invoke() = preferenceRepository.getUserType()
}