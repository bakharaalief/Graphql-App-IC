package com.bakharaalief.graphqlappic.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.apollographql.apollo3.ApolloClient
import com.bakharaalief.app.StoriesQuery
import com.bakharaalief.graphqlappic.data.network.apollo.ApolloConfig
import com.bakharaalief.graphqlappic.domain.model.StoryModel
import com.bakharaalief.graphqlappic.domain.repository.IStoryRepository
import com.bakharaalief.graphqlappic.util.DataMapper.toStoryModel

class StoryRepository : IStoryRepository {
    private lateinit var apolloClient: ApolloClient

    override fun setApolloClient(token: String) {
        apolloClient = ApolloConfig.getApiService(token)
    }

    override fun getStoriesData(): LiveData<Resource<List<StoryModel>>> = liveData {
        emit(Resource.Loading)

        try {
            val response = apolloClient.query(StoriesQuery()).execute()

            if (response.errors?.isNotEmpty() == true) {
                emit(Resource.Error(response.errors?.get(0)?.message ?: "error"))
            } else {
                val stories = response.data?.stories?.edges
                emit(Resource.Success(stories.toStoryModel()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }
}