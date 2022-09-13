package com.bakharaalief.graphqlappic.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.graphqlappic.data.Resource
import com.bakharaalief.graphqlappic.domain.model.LoginModel
import com.bakharaalief.graphqlappic.domain.model.RefreshTokenModel

interface AuthUseCase {

    fun getData(username: String, password: String): LiveData<Resource<LoginModel>>

    fun getRefresh(token: String): LiveData<Resource<RefreshTokenModel>>
}