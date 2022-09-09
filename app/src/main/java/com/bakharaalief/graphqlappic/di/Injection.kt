package com.bakharaalief.graphqlappic.di

import com.bakharaalief.graphqlappic.data.AuthRepository
import com.bakharaalief.graphqlappic.data.network.retrofit.ApiConfig

object Injection {

    private val apiService by lazy { ApiConfig.getApiService() }

    fun provideAuthRepository() = AuthRepository(apiService)
}