package com.bakharaalief.graphqlappic.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.graphqlappic.data.Resource
import com.bakharaalief.graphqlappic.domain.model.StoryModel

interface StoryUseCase {
    fun setApolloClient(token: String)

    fun getStoriesData(): LiveData<Resource<List<StoryModel>>>

    fun likeStory(id: String, isLike: Boolean): LiveData<Resource<String>>

    fun bookmarkStory(id: String, isBookmark: Boolean): LiveData<Resource<String>>
}