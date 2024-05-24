package com.eslam.domain.use_case.local

import com.eslam.domain.repository.local.PreferenceRepository
import com.eslam.domain.util.UserType
import javax.inject.Inject

class SetUserTypeUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {

    suspend operator fun invoke(userType: UserType) {
        preferenceRepository.setUserType(userType = userType)
    }
}