package com.bakharaalief.graphqlappic.util

import com.bakharaalief.app.StoriesQuery
import com.bakharaalief.graphqlappic.data.network.response.LoginResponse
import com.bakharaalief.graphqlappic.data.network.response.RefreshTokenResponse
import com.bakharaalief.graphqlappic.domain.model.LoginModel
import com.bakharaalief.graphqlappic.domain.model.RefreshTokenModel
import com.bakharaalief.graphqlappic.domain.model.StoryModel

object DataMapper {

    fun LoginResponse?.toLoginModel(): LoginModel {
        return this?.let {
            LoginModel(
                it.accessToken,
                it.accessTokenExpiresAt,
                it.refreshToken,
                it.refreshTokenExpiresAt,
                it.tokenType
            )
        } ?: LoginModel("", "", "", "", "")
    }

    fun RefreshTokenResponse?.toRefreshTokenModel(): RefreshTokenModel {
        return this?.let {
            RefreshTokenModel(
                it.accessToken,
                it.accessTokenExpiresAt,
                it.refreshToken,
                it.refreshTokenExpiresAt,
                it.message
            )
        } ?: RefreshTokenModel("", "", "", "", "")
    }

    fun List<StoriesQuery.Edge>?.toStoryModel(): List<StoryModel> {
        return this?.map {
            StoryModel(
                it.id,
                it.title,
                it.hasBeenLiked,
                it.hasBeenBookmarked,
                it.publishedAt.toString()
            )
        } ?: emptyList()
    }
}