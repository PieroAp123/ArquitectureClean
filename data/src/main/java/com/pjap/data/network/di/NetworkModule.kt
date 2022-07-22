package com.pjap.data.network.di

import android.content.Context
import com.pjap.data.BuildConfig
import com.pjap.data.network.ApiConfig
import com.pjap.data.network.repository.LoginRepository
import com.pjap.data.network.repository.UserRepository
import com.pjap.data.network.utils.AUTHORIZATION
import com.pjap.data.network.utils.BASE_URL
import com.pjap.data.network.utils.PLATFORM
import com.pjap.data.network.utils.TIMEOUT
import com.pjap.data.preference.manager.PreferencesManager
import com.pjap.data.preference.utils.PREFERENCE_TOKEN
import com.pjap.domain.repository.LoginRepositoryNetwork
import com.pjap.domain.repository.UserRepositoryNetwork
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { providerHttpLoggingInterceptor() }
    single { providerCache(get()) }
    single { ApiInterceptor(get(), get()) }
    single { providerOkHttpClient(get(), get())}
    single { providerRetrofit(getProperty(BASE_URL), get()) }
    single { providerApi(get()) }

    single<LoginRepositoryNetwork> { LoginRepository(get()) }
    single<UserRepositoryNetwork> {UserRepository(get())}
}

fun providerApi(retrofit: Retrofit): ApiConfig {
    return retrofit.create(ApiConfig::class.java)
}

fun providerRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build()
}

fun providerOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    apiInterceptor: ApiInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(apiInterceptor)
        .build()
}

fun providerCache(context: Context): Cache {
    val cacheSize: Long = 10485760
    return Cache(context.cacheDir, cacheSize)
}

fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}

class ApiInterceptor(private val context: Context, private val sharedPreferences: PreferencesManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Authorization", AUTHORIZATION + sharedPreferences.getString(PREFERENCE_TOKEN))
            .header("x-os", PLATFORM)
            /*.header("x-density", getDensity(context).toString())
            .header("x-width", getWidht(context).toString())
            .header("x-height", getHeight(context).toString())*/
            .build()
        return chain.proceed(request)
    }
}