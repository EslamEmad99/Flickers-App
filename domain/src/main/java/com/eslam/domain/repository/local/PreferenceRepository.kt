package com.eslam.domain.repository.local

import com.eslam.domain.model.User
import com.eslam.domain.util.UserType
import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
    suspend fun getLanguage(): Flow<String>
    suspend fun setLanguage(lang: String)
    suspend fun getToken(): Flow<String>
    suspend fun setToken(token: String)
    suspend fun getUserData(): Flow<User?>
    suspend fun setUserData(user: User?)
    suspend fun getIsLogin(): Flow<Boolean>
    suspend fun setIsLogin(isLogin: Boolean)
    suspend fun getUserType(): Flow<UserType>
    suspend fun setUserType(userType: UserType)
}