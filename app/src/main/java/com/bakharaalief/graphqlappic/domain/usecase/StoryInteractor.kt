package com.bakharaalief.graphqlappic.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.graphqlappic.data.Resource
import com.bakharaalief.graphqlappic.domain.model.StoryModel
import com.bakharaalief.graphqlappic.domain.repository.IStoryRepository

class StoryInteractor(private val storyRepository: IStoryRepository) : StoryUseCase {

    override fun setApolloClient(token: String) = storyRepository.setApolloClient(token)

    override fun getStoriesData(): LiveData<Resource<List<StoryModel>>> =
        storyRepository.getStoriesData()
}