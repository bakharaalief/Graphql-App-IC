package com.bakharaalief.graphqlappic.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bakharaalief.graphqlappic.data.network.payload.LoginPayload
import com.bakharaalief.graphqlappic.data.network.payload.RefreshTokenPayload
import com.bakharaalief.graphqlappic.data.network.response.LoginResponse
import com.bakharaalief.graphqlappic.data.network.response.RefreshTokenResponse
import com.bakharaalief.graphqlappic.data.network.retrofit.ApiService

class AuthRepository(private val apiService: ApiService) {

    fun getData(username: String, password: String): LiveData<Resource<LoginResponse>> = liveData {
        emit(Resource.Loading)

        try {
            val loginPayload = LoginPayload(username, password)
            val response = apiService.login(loginPayload)

            if (response.isSuccessful) {
                emit(Resource.Success(response.body() ?: LoginResponse("", "", "", "", "")))
            } else {
                if (response.code() == 403) emit(Resource.Error("permission denied"))
                else if (response.code() == 401) emit(Resource.Error("email or password not match"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun getRefresh(token: String): LiveData<Resource<RefreshTokenResponse>> = liveData {
        emit(Resource.Loading)

        try {
            val refreshTokenPayload = RefreshTokenPayload(token)
            val response = apiService.refreshToken(refreshTokenPayload)

            if (response.isSuccessful) {
                emit(Resource.Success(response.body() ?: RefreshTokenResponse("", "", "", "", "")))
            } else {
                if (response.code() == 404) emit(Resource.Error("Not Found"))
                else emit(Resource.Error(response.message()))

            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}