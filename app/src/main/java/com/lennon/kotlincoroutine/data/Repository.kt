package com.lennon.kotlincoroutine.data

import com.lennon.kotlincoroutine.RetrofitClient
import com.lennon.kotlincoroutine.data.model.dto.RepositoriesResponse
import com.lennon.kotlincoroutine.data.model.dto.RepositoryResponse
import com.lennon.kotlincoroutine.data.model.vo.RepositoryVO

class RepositoryImpl: Repository {

    private val service = RetrofitClient().create(Service::class.java)

    override suspend fun fetchRepositories(language: String, page: Int): List<RepositoryVO> {
        return service.fetchRepositories().await().response.map { RepositoryVO(it) }
    }

}

interface Repository {
    suspend fun fetchRepositories(language: String, page: Int): List<RepositoryVO>
}
