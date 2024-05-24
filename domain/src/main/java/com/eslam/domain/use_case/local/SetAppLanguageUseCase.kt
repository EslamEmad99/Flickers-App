package com.eslam.domain.use_case.local

import com.eslam.domain.repository.local.PreferenceRepository
import javax.inject.Inject

class SetAppLanguageUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {

    suspend operator fun invoke(lang: String) {
        preferenceRepository.setLanguage(lang = lang)
    }
}