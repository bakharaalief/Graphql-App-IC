package com.bakharaalief.graphqlappic.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bakharaalief.graphqlappic.data.network.payload.LoginPayload
import com.bakharaalief.graphqlappic.data.network.response.LoginResponse
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
}