package com.lennon.kotlincoroutine.data.model

import com.lennon.kotlincoroutine.data.model.dto.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("search/repositories?q=language:{language}&sort=stars&page={page}")
    fun listRepos(
        @Path("user") language: String,
        @Path("page") page: Int
    ): Call<List<RepositoryResponse>>
}
