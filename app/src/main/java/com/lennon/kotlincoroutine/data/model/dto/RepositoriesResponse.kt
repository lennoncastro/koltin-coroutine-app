package com.lennon.kotlincoroutine.data.model.dto

import com.google.gson.annotations.SerializedName

class RepositoriesResponse(
    @SerializedName("items") val response: List<RepositoryResponse>
)
