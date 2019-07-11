package com.lennon.kotlincoroutine.di

import com.lennon.kotlincoroutine.RetrofitClient
import com.lennon.kotlincoroutine.viewmodel.RepositoryViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val myModule : Module = module {
    factory { RepositoryViewModel() }
    single { RetrofitClient }
}
