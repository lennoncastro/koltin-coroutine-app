package com.lennon.kotlincoroutine.viewmodel

import com.lennon.kotlincoroutine.data.RepositoryImpl
import com.lennon.kotlincoroutine.data.model.vo.RepositoryVO
import kotlinx.coroutines.launch

class RepositoryViewModel(
    private val repository: RepositoryImpl
) : RequestViewModel<List<RepositoryVO>>() {

    fun fetchRepositories() {

        viewModeScope.launch {

            showLoading.value = true

            try {
                successResponse.value = repository.fetchRepositories("Java", 0)
            } catch (throwable: Throwable) {
                errorResponse.value = throwable
            }

            showLoading.value = false
        }
    }
}
