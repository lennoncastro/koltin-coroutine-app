package com.lennon.kotlincoroutine.data

import com.lennon.kotlincoroutine.data.model.dto.RepositoriesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Service {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun fetchRepositories(): Deferred<RepositoriesResponse>
}
