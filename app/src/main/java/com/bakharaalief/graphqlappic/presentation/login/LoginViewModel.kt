package com.bakharaalief.graphqlappic.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bakharaalief.graphqlappic.data.AuthRepository
import com.bakharaalief.graphqlappic.data.userPref.UserModel
import com.bakharaalief.graphqlappic.data.userPref.UserPreferences
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    fun login(username: String, password: String) = authRepository.getData(username, password)

    fun refreshToken(token: String) = authRepository.getRefresh(token)

    fun getUserPref() = userPreferences.getUser().asLiveData()

    fun saveUser(userModel: UserModel) {
        viewModelScope.launch {
            userPreferences.saveUser(userModel)
        }
    }
}