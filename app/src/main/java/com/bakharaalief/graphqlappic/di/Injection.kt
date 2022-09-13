package com.bakharaalief.graphqlappic.di

import com.bakharaalief.graphqlappic.data.AuthRepository
import com.bakharaalief.graphqlappic.data.StoryRepository
import com.bakharaalief.graphqlappic.data.network.retrofit.ApiConfig
import com.bakharaalief.graphqlappic.domain.usecase.AuthInteractor
import com.bakharaalief.graphqlappic.domain.usecase.AuthUseCase
import com.bakharaalief.graphqlappic.domain.usecase.StoryInteractor
import com.bakharaalief.graphqlappic.domain.usecase.StoryUseCase

object Injection {

    private val apiService by lazy { ApiConfig.getApiService() }

    private fun provideAuthRepository() = AuthRepository(apiService)

    fun provideAuthUseCase(): AuthUseCase = AuthInteractor(provideAuthRepository())

    private fun provideStoryRepository() = StoryRepository()

    fun provideStoryUseCase(): StoryUseCase = StoryInteractor(provideStoryRepository())
}