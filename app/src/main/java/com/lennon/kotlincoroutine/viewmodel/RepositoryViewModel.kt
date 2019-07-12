package com.lennon.kotlincoroutine.viewmodel

import com.lennon.kotlincoroutine.data.ErrorResponse
import com.lennon.kotlincoroutine.data.RepositoryImpl
import com.lennon.kotlincoroutine.data.RequestResponseApi
import com.lennon.kotlincoroutine.data.model.vo.RepositoryVO
import kotlinx.coroutines.launch

class RepositoryViewModel(
    private val repository: RepositoryImpl,
    private val requestResponse: RequestResponseApi<List<RepositoryVO>>
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
