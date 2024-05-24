package com.eslam.flickers_app.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslam.domain.model.User
import com.eslam.domain.use_case.local.GetAuthUserDataUseCase
import com.eslam.domain.use_case.local.GetIsLoginUseCase
import com.eslam.domain.use_case.local.GetTokenUseCase
import com.eslam.domain.use_case.local.GetUserTypeUseCase
import com.eslam.domain.use_case.local.SetAppLanguageUseCase
import com.eslam.domain.use_case.local.SetAuthUserDataUseCase
import com.eslam.domain.use_case.local.SetIsLoginUseCase
import com.eslam.domain.use_case.local.SetTokenUseCase
import com.eslam.domain.use_case.local.SetUserTypeUseCase
import com.eslam.domain.util.UserType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val getIsLoginUseCase: GetIsLoginUseCase,
    private val getUserTypeUseCase: GetUserTypeUseCase,
    private val getUserDataUseCase: GetAuthUserDataUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val setAppLanguageUseCase: SetAppLanguageUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val setAuthUserDataUseCase: SetAuthUserDataUseCase,
    private val setIsLoginUseCase: SetIsLoginUseCase,
    private val setUserTypeUseCase: SetUserTypeUseCase
) : ViewModel() {

    fun getIsLogin() = runBlocking { getIsLoginUseCase().first() }

    fun getUserType() = runBlocking { getUserTypeUseCase().first() }

    fun getUserData() = runBlocking { getUserDataUseCase().first() }

    suspend fun getUserDataAsFlow() = getUserDataUseCase()

    fun getToken() = runBlocking { getTokenUseCase().first() }

    fun setAppLanguage(language: String) {
        viewModelScope.launch {
            setAppLanguageUseCase(lang = language)
        }
    }

    fun setAuthUserToken(token: String) {
        viewModelScope.launch {
            setTokenUseCase(token = token)
        }
    }

    fun setAuthUserData(user: User?) {
        viewModelScope.launch {
            setAuthUserDataUseCase(user = user)
        }
    }

    fun setIsLogin(isLogin: Boolean) {
        viewModelScope.launch {
            setIsLoginUseCase(isLogin = isLogin)
        }
    }

    fun setUserType(userType: UserType) {
        viewModelScope.launch {
            setUserTypeUseCase(userType = userType)
        }
    }
}
