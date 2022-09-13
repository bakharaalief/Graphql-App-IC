package com.bakharaalief.graphqlappic.domain.repository

import androidx.lifecycle.LiveData
import com.bakharaalief.graphqlappic.data.Resource
import com.bakharaalief.graphqlappic.domain.model.StoryModel

interface IStoryRepository {
    fun setApolloClient(token: String)

    fun getStoriesData() : LiveData<Resource<List<StoryModel>>>
}