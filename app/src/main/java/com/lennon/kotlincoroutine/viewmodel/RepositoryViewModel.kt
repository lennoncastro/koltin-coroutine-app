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

class RepositoryViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Main

    private val viewModeScope = CoroutineScope(Main + SupervisorJob())

    var response = MutableLiveData<List<RepositoryVO>>()

    var repository = RepositoryImpl()

    fun fetchRepositories() {
        viewModeScope.launch {
            response.value = repository.fetchRepositories("Java", 0)
        }
    }

}
