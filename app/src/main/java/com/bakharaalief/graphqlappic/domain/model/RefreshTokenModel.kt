package com.bakharaalief.graphqlappic.domain.model

data class RefreshTokenModel(
    val accessToken: String,
    val accessTokenExpiresAt: String,
    val refreshToken: String,
    val refreshTokenExpiresAt: String,
    val message: String
)
