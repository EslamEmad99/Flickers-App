package com.eslam.domain.use_case.local

import com.eslam.domain.repository.local.PreferenceRepository
import javax.inject.Inject

class SetIsLoginUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {

    suspend operator fun invoke(isLogin: Boolean) {
        preferenceRepository.setIsLogin(isLogin = isLogin)
    }
}