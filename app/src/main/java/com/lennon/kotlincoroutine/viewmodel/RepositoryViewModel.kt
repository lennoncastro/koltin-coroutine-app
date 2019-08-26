package com.lennon.kotlincoroutine.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lennon.kotlincoroutine.data.ErrorResponse
import com.lennon.kotlincoroutine.data.Repository
import com.lennon.kotlincoroutine.data.RequestResponse
import com.lennon.kotlincoroutine.ui.vo.RepositoryVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RepositoryViewModel(
    private val repository: Repository,
    private val requestResponse: RequestResponse<List<RepositoryVO>>,
    private val viewModeScope: CoroutineScope
) : ViewModel() {

    fun fetchRepositories() {

        viewModeScope.launch {

            requestResponse.showLoading.value = true

            try {
                requestResponse.successResponse.value = repository.fetchRepositories()
            } catch (throwable: Throwable) {
                requestResponse.errorResponse.value = ErrorResponse(throwable, true)
            }

            requestResponse.showLoading.value = false
        }
    }

    fun onSuccess(): LiveData<List<RepositoryVO>> = requestResponse.successResponse

    fun onError(): LiveData<ErrorResponse> = requestResponse.errorResponse

    fun showLoading(): LiveData<Boolean> = requestResponse.showLoading
}
