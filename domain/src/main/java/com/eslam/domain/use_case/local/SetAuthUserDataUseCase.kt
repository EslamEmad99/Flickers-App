package com.eslam.domain.use_case.local

import com.eslam.domain.model.User
import com.eslam.domain.repository.local.PreferenceRepository
import javax.inject.Inject

class SetAuthUserDataUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {

    suspend operator fun invoke(user: User?) {
        preferenceRepository.setUserData(user = user)
    }
}