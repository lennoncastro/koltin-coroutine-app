package com.lennon.kotlincoroutine.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lennon.kotlincoroutine.data.ErrorResponse
import com.lennon.kotlincoroutine.data.Repository
import com.lennon.kotlincoroutine.data.RequestResponse
import com.lennon.kotlincoroutine.ui.vo.RepositoryVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RepositoryViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var observerSuccessResponse: Observer<List<RepositoryVO>>

    @Mock
    private lateinit var observerErrorResponse: Observer<ErrorResponse>

    @Mock
    private lateinit var observerShowLoading: Observer<Boolean>

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var requestResponse: RequestResponse<List<RepositoryVO>>

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private lateinit var target: RepositoryViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setupViewModel()
    }

    @Test
    fun whenFetchRepositories_verifyIfShowLoadingChanges() = runBlocking {

        mockShowLoadingBehavior()

        mockSuccessResponse()

        target.showLoading().observeForever(observerShowLoading)

        fetchRepositories()

        verify(observerShowLoading, times(2)).onChanged(any())
    }

    @Test
    fun whenFetchRepositories_verifyIfSuccessResponseChanges() = runBlocking {

        mockShowLoadingBehavior()

        mockSuccessResponse()

        target.onSuccess().observeForever(observerSuccessResponse)

        fetchRepositories()

        verify(observerSuccessResponse).onChanged(anyList())
    }

    @Test
    fun onFetchRepositoriesError_verifyIfErrorResponseChanges() = runBlocking {

        mockShowLoadingBehavior()

        mockErrorResponse()

        target.onError().observeForever(observerErrorResponse)

        fetchRepositories()

        verify(observerErrorResponse).onChanged(any())
    }

    private fun fetchRepositories() {
        target.fetchRepositories()
    }

    private fun setupViewModel() {
        mockRepositoryResponse()
        target = RepositoryViewModel(repository, requestResponse, scope)
    }

    private fun mockRepositoryResponse() {
        runBlocking {
            `when`(repository.fetchRepositories("Kotlin", 0)).thenReturn(listOf())
        }
    }

    private fun mockSuccessResponse() {
        `when`(requestResponse.successResponse).thenReturn(MutableLiveData())
    }

    private fun mockErrorResponse() {
        `when`(requestResponse.errorResponse).thenReturn(MutableLiveData())
    }

    private fun mockShowLoadingBehavior() {
        `when`(requestResponse.showLoading).thenReturn(MutableLiveData())
    }
}
