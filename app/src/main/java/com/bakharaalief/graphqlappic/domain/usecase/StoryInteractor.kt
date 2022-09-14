package com.bakharaalief.graphqlappic.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.graphqlappic.data.Resource
import com.bakharaalief.graphqlappic.domain.model.StoryModel
import com.bakharaalief.graphqlappic.domain.repository.IStoryRepository

class StoryInteractor(private val storyRepository: IStoryRepository) : StoryUseCase {

    override fun setApolloClient(token: String) = storyRepository.setApolloClient(token)

    override fun getStoriesData(): LiveData<Resource<List<StoryModel>>> =
        storyRepository.getStoriesData()

    override fun likeStory(id: String, isLike: Boolean): LiveData<Resource<Boolean>> =
        if (isLike) storyRepository.unLikeStory(id) else storyRepository.likeStory(id)

    override fun bookmarkStory(id: String, isBookmark: Boolean): LiveData<Resource<Boolean>> =
        if (isBookmark) storyRepository.unBookmarkStory(id) else storyRepository.bookmarkStory(id)
}