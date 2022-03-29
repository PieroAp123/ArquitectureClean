package com.pjap.domain.di

import com.pjap.domain.usecases.LoginUseCase
import org.koin.dsl.module

val domainModule = module {
    single { LoginUseCase(get(), get()) }
}