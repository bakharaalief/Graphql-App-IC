package com.bakharaalief.graphqlappic.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.graphqlappic.data.Resource
import com.bakharaalief.graphqlappic.domain.model.LoginModel
import com.bakharaalief.graphqlappic.domain.model.RefreshTokenModel
import com.bakharaalief.graphqlappic.domain.repository.IAuthRepository

class AuthInteractor(private val authRepository: IAuthRepository) : AuthUseCase {

    override fun getData(
        username: String,
        password: String
    ): LiveData<Resource<LoginModel>> = authRepository.getData(username, password)

    override fun getRefresh(token: String): LiveData<Resource<RefreshTokenModel>> =
        authRepository.getRefresh(token)
}