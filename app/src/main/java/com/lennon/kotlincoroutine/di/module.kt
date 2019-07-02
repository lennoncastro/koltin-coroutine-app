package com.lennon.kotlincoroutine.di

import com.lennon.kotlincoroutine.viewmodel.RepositoryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {
    viewModel { RepositoryViewModel() }
}