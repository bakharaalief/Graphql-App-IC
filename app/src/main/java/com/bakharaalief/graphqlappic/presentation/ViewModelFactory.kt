package com.bakharaalief.graphqlappic.presentation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bakharaalief.graphqlappic.data.userPref.UserPreferences
import com.bakharaalief.graphqlappic.di.Injection
import com.bakharaalief.graphqlappic.domain.usecase.AuthUseCase
import com.bakharaalief.graphqlappic.domain.usecase.StoryUseCase
import com.bakharaalief.graphqlappic.presentation.login.LoginViewModel
import com.bakharaalief.graphqlappic.presentation.main.MainViewModel

class ViewModelFactory(
    private val authUseCase: AuthUseCase,
    private val storyUseCase: StoryUseCase,
    private val userPreferences: UserPreferences,
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                authUseCase, userPreferences
            ) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                storyUseCase, userPreferences
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
                    Injection.provideAuthUseCase(),
                    Injection.provideStoryUseCase(),
                    UserPreferences.getInstance(pref)
                )
            }.also { instance = it }
    }
}