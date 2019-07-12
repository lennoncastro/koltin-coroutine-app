package com.lennon.kotlincoroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lennon.kotlincoroutine.data.model.vo.RepositoryVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class RequestViewModel: ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    val viewModeScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    var successResponse = MutableLiveData<List<RepositoryVO>>()

    var errorResponse = MutableLiveData<Throwable>()

    var showLoading = MutableLiveData<Boolean>()
}