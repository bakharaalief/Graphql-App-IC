package com.bakharaalief.graphqlappic.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.apollographql.apollo3.ApolloClient
import com.bakharaalief.app.StoriesQuery
import com.bakharaalief.graphqlappic.data.network.apollo.ApolloConfig

class StoryRepository {

    private lateinit var apolloClient: ApolloClient

    fun setApolloClient(token: String) {
        apolloClient = ApolloConfig.getApiService(token)
    }

    fun getStoriesData(): LiveData<Resource<List<StoriesQuery.Edge>>> = liveData {
        emit(Resource.Loading)

        try {
            val response = apolloClient.query(StoriesQuery()).execute()

            if (response.errors?.isNotEmpty() == true) {
                emit(Resource.Error(response.errors?.get(0)?.message ?: "error"))
            } else {
                val stories = response.data?.stories?.edges?.filterNotNull() ?: emptyList()
                emit(Resource.Success(stories))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }
}