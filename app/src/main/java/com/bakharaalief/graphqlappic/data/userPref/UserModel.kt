package com.bakharaalief.graphqlappic.data.userPref

data class UserModel(
    val accessToken: String,
    val accessTokenExpired: String,
    val refreshToken: String,
    val refreshTokenExpired: String,
    val isUserLogin: Boolean
)
