package com.lennon.kotlincoroutine.viewmodel

import com.lennon.kotlincoroutine.data.ErrorResponse
import com.lennon.kotlincoroutine.data.Repository
import com.lennon.kotlincoroutine.data.RequestResponse
import com.lennon.kotlincoroutine.data.model.vo.RepositoryVO
import kotlinx.coroutines.launch

class RepositoryViewModel(
    private val repository: Repository,
    private val requestResponse: RequestResponse<List<RepositoryVO>>
) : CoroutineViewModel() {

    fun fetchRepositories() {

        viewModeScope.launch {

            requestResponse.showLoading.value = true

            try {
                requestResponse.successResponse.value = repository.fetchRepositories("Kotlin", 0)
            } catch (throwable: Throwable) {
                requestResponse.errorResponse.value = ErrorResponse(throwable, true)
            }

            requestResponse.showLoading.value = false
        }
    }

    fun onSuccess() = requestResponse.successResponse

    fun onError() = requestResponse.errorResponse

    fun showLoading() = requestResponse.showLoading
}
