package com.lennon.kotlincoroutine.data.model.dto

import com.google.gson.annotations.SerializedName

class RepositoryResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("forks") val forks: Long,
    @SerializedName("stargazersCount") val stargazersCount: Long
)
