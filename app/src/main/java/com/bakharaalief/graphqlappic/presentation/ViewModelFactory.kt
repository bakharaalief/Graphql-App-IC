package com.bakharaalief.graphqlappic.presentation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bakharaalief.graphqlappic.data.AuthRepository
import com.bakharaalief.graphqlappic.data.userPref.UserPreferences
import com.bakharaalief.graphqlappic.di.Injection
import com.bakharaalief.graphqlappic.presentation.login.LoginViewModel

class ViewModelFactory(
    private val authRepository: AuthRepository,
    private val userPreferences: UserPreferences
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                authRepository, userPreferences
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(pref: DataStore<Preferences>): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideAuthRepository(),
                    UserPreferences.getInstance(pref)
                )
            }.also { instance = it }
    }
}