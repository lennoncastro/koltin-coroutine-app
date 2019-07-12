package com.lennon.kotlincoroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lennon.kotlincoroutine.data.RepositoryImpl
import com.lennon.kotlincoroutine.data.model.vo.RepositoryVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RepositoryViewModel(
    private val repository: RepositoryImpl
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Main

    private val viewModeScope = CoroutineScope(Main + SupervisorJob())

    var successResponse = MutableLiveData<List<RepositoryVO>>()

    var errorResponse = MutableLiveData<Throwable>()

    var showLoading = MutableLiveData<Boolean>()

    fun fetchRepositories() {
        showLoading.value = true
        viewModeScope.launch {
            try {
                showLoading.value = false
                successResponse.value = repository.fetchRepositories("Java", 0)
            } catch (throwable: Throwable) {
                showLoading.value = false
                errorResponse.value = throwable
            }
        }
    }
}
