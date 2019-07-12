package com.lennon.kotlincoroutine.data

import androidx.lifecycle.MutableLiveData

interface RequestResponse<T> {

    val successResponse: MutableLiveData<T>

    val errorResponse: MutableLiveData<ErrorResponse>

    val showLoading: MutableLiveData<Boolean>
}

class RequestResponseApi<T>: RequestResponse<T> {

    override var successResponse = MutableLiveData<T>()

    override var errorResponse = MutableLiveData<ErrorResponse>()

    override var showLoading = MutableLiveData<Boolean>()
}

class ErrorResponse(
    val throwable: Throwable,
    val showErrorMessage: Boolean = true
)
