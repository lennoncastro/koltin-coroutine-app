package com.lennon.kotlincoroutine.data

import com.lennon.kotlincoroutine.RetrofitClient
import com.lennon.kotlincoroutine.data.model.vo.RepositoryVO

class RepositoryImpl(
    private val service: RetrofitClient
): Repository {

    override suspend fun fetchRepositories(language: String, page: Int): List<RepositoryVO> {
        return service.retrofit.create(Service::class.java)
            .fetchRepositories()
            .await()
            .response.map { RepositoryVO(it) }
    }
}

interface Repository {
    suspend fun fetchRepositories(language: String, page: Int): List<RepositoryVO>
}
