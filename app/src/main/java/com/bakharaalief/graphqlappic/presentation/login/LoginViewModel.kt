package com.bakharaalief.graphqlappic.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bakharaalief.graphqlappic.data.userPref.UserModel
import com.bakharaalief.graphqlappic.data.userPref.UserPreferences
import com.bakharaalief.graphqlappic.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authUseCase: AuthUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    fun login(username: String, password: String) = authUseCase.getData(username, password)

    fun refreshToken(token: String) = authUseCase.getRefresh(token)

    fun getUserPref() = userPreferences.getUser().asLiveData()

    fun saveUser(userModel: UserModel) {
        viewModelScope.launch {
            userPreferences.saveUser(userModel)
        }
    }
}