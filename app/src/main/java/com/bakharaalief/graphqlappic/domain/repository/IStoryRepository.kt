package com.bakharaalief.graphqlappic.domain.repository

import androidx.lifecycle.LiveData
import com.bakharaalief.graphqlappic.data.Resource
import com.bakharaalief.graphqlappic.domain.model.StoryModel

interface IStoryRepository {
    fun setApolloClient(token: String)

    fun getStoriesData(): LiveData<Resource<List<StoryModel>>>

    fun likeStory(id: String): LiveData<Resource<Boolean>>

    fun unLikeStory(id: String): LiveData<Resource<Boolean>>

    fun bookmarkStory(id: String): LiveData<Resource<Boolean>>

    fun unBookmarkStory(id: String): LiveData<Resource<Boolean>>
}