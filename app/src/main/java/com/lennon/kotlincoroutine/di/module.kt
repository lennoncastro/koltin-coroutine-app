package com.lennon.kotlincoroutine.di

import com.lennon.kotlincoroutine.RetrofitClient
import com.lennon.kotlincoroutine.data.RepositoryImpl
import com.lennon.kotlincoroutine.data.RequestResponseApi
import com.lennon.kotlincoroutine.data.model.vo.RepositoryVO
import com.lennon.kotlincoroutine.ui.RepositoryAdapter
import com.lennon.kotlincoroutine.viewmodel.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val myModule : Module = module {

    viewModel {
        RepositoryViewModel(get(), get())
    }

    single {
        RetrofitClient
    }

    single {
        RepositoryImpl(get())
    }

    single {
        RepositoryAdapter()
    }

    single {
        RequestResponseApi<List<RepositoryVO>>()
    }
}
