package com.bakharaalief.graphqlappic.data.network.payload

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshTokenPayload(
    @Json(name = "refresh_token") val refreshToken: String
)
