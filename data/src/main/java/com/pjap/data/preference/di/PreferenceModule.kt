package com.pjap.data.preference.di

import com.pjap.data.preference.UserPreference
import com.pjap.data.preference.manager.PreferencesManager
import com.pjap.domain.repository.LoginRepositoryPreference
import org.koin.dsl.module

val preferenceModule = module {
    single { PreferencesManager(get()) }
    single<LoginRepositoryPreference> { UserPreference(get()) }
}