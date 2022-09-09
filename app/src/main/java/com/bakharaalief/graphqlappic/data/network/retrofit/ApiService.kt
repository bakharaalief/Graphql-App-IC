package com.bakharaalief.graphqlappic.data.network.retrofit

import com.bakharaalief.graphqlappic.data.network.payload.LoginPayload
import com.bakharaalief.graphqlappic.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body payload: LoginPayload
    ): Response<LoginResponse>
}