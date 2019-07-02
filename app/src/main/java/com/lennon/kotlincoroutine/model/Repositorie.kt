package com.lennon.kotlincoroutine.model

import com.google.gson.annotations.SerializedName

class Repositorie(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("forks") val forks: Long,
    @SerializedName("stargazersCount") val stargazersCount: Long
)
