package com.pjap.deliveryaplication.application

import android.app.Application
import com.pjap.data.network.di.networkModule
import com.pjap.data.network.utils.BASE_URL
import com.pjap.data.preference.di.preferenceModule
import com.pjap.deliveryaplication.di.viewModelsModule
import com.pjap.domain.di.domainModule
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class DeliveryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DeliveryApplication)
            modules(listOf(preferenceModule, networkModule, domainModule, viewModelsModule))
        }
        //getKoin().setProperty(BASE_URL, "https://us-central1-mkt-003001-00813.cloudfunctions.net/")
        getKoin().setProperty(BASE_URL, "http://192.168.18.4:3000/api/users/")

    }
}