package com.eslam.domain.use_case.local

import com.eslam.domain.repository.local.PreferenceRepository
import javax.inject.Inject

class SetTokenUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {

    suspend operator fun invoke(token: String) {
        preferenceRepository.setToken(token = token)
    }
}