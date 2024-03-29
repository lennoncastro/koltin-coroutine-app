package com.lennon.kotlincoroutine.data

import com.lennon.kotlincoroutine.RetrofitClient
import com.lennon.kotlincoroutine.ui.vo.RepositoryVO

class RepositoryImpl(
    private val retrofitClient: RetrofitClient
): Repository {

    override suspend fun fetchRepositories(): List<RepositoryVO> {
        return retrofitClient.invoke().create(Service::class.java)
            .fetchRepositories()
            .await()
            .response.map { RepositoryVO(it) }
    }
}

interface Repository {
    suspend fun fetchRepositories(): List<RepositoryVO>
}
