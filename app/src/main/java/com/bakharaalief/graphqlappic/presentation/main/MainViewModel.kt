package com.bakharaalief.graphqlappic.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bakharaalief.graphqlappic.data.userPref.UserPreferences
import com.bakharaalief.graphqlappic.domain.usecase.StoryUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val storyUseCase: StoryUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    fun getUserPref() = userPreferences.getUser().asLiveData()

    fun logOut() {
        viewModelScope.launch {
            userPreferences.logOut()
        }
    }

    fun setToken(token: String) = storyUseCase.setApolloClient(token)

    fun getStories() = storyUseCase.getStoriesData()
}