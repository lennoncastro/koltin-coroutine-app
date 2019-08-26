package com.lennon.kotlincoroutine.data.model.dto

import com.google.gson.annotations.SerializedName

class OwnerResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
