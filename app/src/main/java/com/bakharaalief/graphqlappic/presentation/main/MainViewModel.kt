package com.bakharaalief.graphqlappic.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bakharaalief.graphqlappic.data.StoryRepository
import com.bakharaalief.graphqlappic.data.userPref.UserPreferences
import kotlinx.coroutines.launch

class MainViewModel(
    private val storyRepository: StoryRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    fun getUserPref() = userPreferences.getUser().asLiveData()

    fun logOut() {
        viewModelScope.launch {
            userPreferences.logOut()
        }
    }

    fun setToken(token: String) = storyRepository.setApolloClient(token)

    fun getStories() = storyRepository.getStoriesData()
}