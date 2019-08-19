package com.lennon.kotlincoroutine.ui.vo

import com.lennon.kotlincoroutine.data.model.dto.RepositoryResponse

class RepositoryVO(
    val name: String,
    val description: String,
    val login: String,
    val forks: Long,
    val stars: Long,
    val avatar: String
) {

    companion object {
        operator fun invoke(response: RepositoryResponse): RepositoryVO {
            return RepositoryVO(
                name = response.name,
                description = response.description,
                login = response.name,
                forks = response.forks,
                stars = response.stargazersCount,
                avatar = response.owner.avatarUrl
            )
        }
    }

}
