package com.bakharaalief.graphqlappic.data.network.payload

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginPayload(
    @Json(name = "email")
    var email: String,

    @Json(name = "password")
    var password: String
)
