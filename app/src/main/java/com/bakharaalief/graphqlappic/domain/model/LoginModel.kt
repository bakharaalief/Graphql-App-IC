package com.bakharaalief.graphqlappic.domain.model

data class LoginModel(
    val accessToken: String,
    val accessTokenExpiresAt: String,
    val refreshToken: String,
    val refreshTokenExpiresAt: String,
    val tokenType: String
)
