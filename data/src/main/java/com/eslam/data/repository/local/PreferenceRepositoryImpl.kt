package com.eslam.data.repository.local

import com.eslam.data.data_source.local.PreferenceDataSource
import com.eslam.data.util.Languages.ARABIC
import com.eslam.data.util.PreferenceConstants.IS_LOGIN
import com.eslam.data.util.PreferenceConstants.LANGUAGE
import com.eslam.data.util.PreferenceConstants.TOKEN
import com.eslam.data.util.PreferenceConstants.USER_DATA
import com.eslam.data.util.PreferenceConstants.USER_TYPE
import com.eslam.domain.model.User
import com.eslam.domain.repository.local.PreferenceRepository
import com.eslam.domain.util.UserType
import com.eslam.domain.util.fromJson
import com.eslam.domain.util.toJson
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(private val preferenceDataSource: PreferenceDataSource) :
    PreferenceRepository {

    override suspend fun getLanguage() = flow {
        preferenceDataSource.getValue(LANGUAGE, ARABIC).collect {
            emit(it as String)
        }
    }

    override suspend fun setLanguage(lang: String) {
        return preferenceDataSource.setValue(LANGUAGE, lang)
    }

    override suspend fun getToken() = flow {
        preferenceDataSource.getValue(TOKEN, "").collect {
            emit(it as String)
        }
    }

    override suspend fun setToken(token: String) {
        preferenceDataSource.setValue(TOKEN, token)
    }

    override suspend fun getUserData() = flow {
        preferenceDataSource.getValue(USER_DATA, "").collect {
            val userAsString = it as String
            emit(
                if (userAsString.isBlank()) {
                    null
                } else {
                    it.fromJson<User>()
                }
            )
        }
    }

    override suspend fun setUserData(user: User?) {
        preferenceDataSource.setValue(USER_DATA, user?.toJson() ?: "")
    }

    override suspend fun getIsLogin() = flow {
        preferenceDataSource.getValue(IS_LOGIN, false).collect {
            emit(it as Boolean)
        }
    }

    override suspend fun setIsLogin(isLogin: Boolean) {
        preferenceDataSource.setValue(IS_LOGIN, isLogin)
    }

    override suspend fun getUserType() = flow {
        preferenceDataSource.getValue(USER_TYPE, UserType.USER.name).collect {
            val userType = it as String
            emit(if (userType == UserType.USER.name) UserType.USER else UserType.DOCTOR)
        }
    }

    override suspend fun setUserType(userType: UserType) {
        preferenceDataSource.setValue(USER_TYPE, userType.name)
    }
}