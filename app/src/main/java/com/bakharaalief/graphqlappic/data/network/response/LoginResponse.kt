package com.bakharaalief.graphqlappic.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(

	@Json(name="access_token")
	val accessToken: String,

	@Json(name="access_token_expires_at")
	val accessTokenExpiresAt: String,

	@Json(name="refresh_token")
	val refreshToken: String,

	@Json(name="refresh_token_expires_at")
	val refreshTokenExpiresAt: String,

	@Json(name="token_type")
	val tokenType: String
)
