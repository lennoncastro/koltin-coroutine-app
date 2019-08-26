package com.lennon.kotlincoroutine.di

import com.lennon.kotlincoroutine.RetrofitClient
import com.lennon.kotlincoroutine.data.Repository
import com.lennon.kotlincoroutine.data.RepositoryImpl
import com.lennon.kotlincoroutine.data.RequestResponse
import com.lennon.kotlincoroutine.data.RequestResponseApi
import com.lennon.kotlincoroutine.ui.RepositoryAdapter
import com.lennon.kotlincoroutine.ui.vo.RepositoryVO
import com.lennon.kotlincoroutine.viewmodel.RepositoryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val myModule: Module = module {

    viewModel {
        RepositoryViewModel(get(), get(), get())
    }

    single {
        RetrofitClient
    }

    single<Repository> {
        RepositoryImpl(get())
    }

    single {
        RepositoryAdapter()
    }

    single<RequestResponse<List<RepositoryVO>>> {
        RequestResponseApi()
    }

    single {
        CoroutineScope(Dispatchers.Main + SupervisorJob())
    }
}
