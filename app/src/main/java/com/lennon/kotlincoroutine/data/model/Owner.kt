package com.lennon.kotlincoroutine.data.model

import com.google.gson.annotations.SerializedName

class Owner(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)

