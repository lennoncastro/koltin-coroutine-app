package com.lennon.kotlincoroutine.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lennon.kotlincoroutine.data.Repository
import com.lennon.kotlincoroutine.data.RequestResponse
import com.lennon.kotlincoroutine.data.model.vo.RepositoryVO
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.Mockito.anyList
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RepositoryViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var observer: Observer<List<RepositoryVO>>

    @Mock
    private lateinit var observerLoading: Observer<Boolean>

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var requestResponse: RequestResponse<List<RepositoryVO>>

    private lateinit var target: RepositoryViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setupViewModel()
    }

    @Test
    fun whenFetchRepositories_verifyIfShowLoadingChanges() = runBlocking {

        `when`(requestResponse.showLoading).thenReturn(MutableLiveData())
        `when`(requestResponse.successResponse).thenReturn(MutableLiveData())

        target.showLoading().observeForever(observerLoading)

        target.fetchRepositories()

        verify(observerLoading, times(2)).onChanged(any())
    }

    @Test
    fun whenFetchRepositories_verifyIfSuccessResponseChanges() = runBlocking {

        `when`(requestResponse.showLoading).thenReturn(MutableLiveData())
        `when`(requestResponse.successResponse).thenReturn(MutableLiveData())

        target.onSuccess().observeForever(observer)

        target.fetchRepositories()

        verify(observer).onChanged(anyList())
    }

    private fun setupViewModel() {
        mockRepositoryResponse()
        target = RepositoryViewModel(repository, requestResponse)
    }

    private fun mockRepositoryResponse() {
        runBlocking {
            `when`(repository.fetchRepositories("Kotlin", 0)).thenReturn(listOf())
        }
    }

}
