package com.bakharaalief.graphqlappic.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bakharaalief.graphqlappic.data.network.payload.LoginPayload
import com.bakharaalief.graphqlappic.data.network.payload.RefreshTokenPayload
import com.bakharaalief.graphqlappic.data.network.retrofit.ApiService
import com.bakharaalief.graphqlappic.domain.model.LoginModel
import com.bakharaalief.graphqlappic.domain.model.RefreshTokenModel
import com.bakharaalief.graphqlappic.domain.repository.IAuthRepository
import com.bakharaalief.graphqlappic.util.DataMapper.toLoginModel
import com.bakharaalief.graphqlappic.util.DataMapper.toRefreshTokenModel

class AuthRepository(private val apiService: ApiService) : IAuthRepository {


    override fun getData(
        username: String,
        password: String
    ): LiveData<Resource<LoginModel>> = liveData {
        emit(Resource.Loading)

        try {
            val loginPayload = LoginPayload(username, password)
            val response = apiService.login(loginPayload)

            if (response.isSuccessful) {
                emit(Resource.Success(response.body().toLoginModel()))
            } else {
                if (response.code() == 403) emit(Resource.Error("permission denied"))
                else if (response.code() == 401) emit(Resource.Error("email or password not match"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    override fun getRefresh(token: String): LiveData<Resource<RefreshTokenModel>> = liveData {
        emit(Resource.Loading)

        try {
            val refreshTokenPayload = RefreshTokenPayload(token)
            val response = apiService.refreshToken(refreshTokenPayload)

            if (response.isSuccessful) {
                emit(Resource.Success(response.body().toRefreshTokenModel()))
            } else {
                if (response.code() == 404) emit(Resource.Error("Not Found"))
                else emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}