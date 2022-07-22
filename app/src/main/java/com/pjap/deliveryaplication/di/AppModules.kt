package com.pjap.deliveryaplication.di

import com.pjap.deliveryaplication.screens.login.LoginViewModel
import com.pjap.deliveryaplication.utils.DispatcherProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    single { DispatcherProvider() }
    viewModel { LoginViewModel(get(), get()) }
}