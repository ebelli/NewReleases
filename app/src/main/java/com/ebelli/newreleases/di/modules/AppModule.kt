package com.ebelli.newreleases.di.modules

import com.ebelli.newreleases.data.remote.ApiDataStore
import com.ebelli.newreleases.data.remote.ApiDataStoreImpl
import com.ebelli.newreleases.data.remote.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://api.spotify.com/v1/browse/"
private const val TOKEN = "BQD7mKPSuAmXWGA--_LLzyMPV7rwxWf4zQBs3J1SWAA00WACWOb5j9CYpPS_nMECJL4lmXyA2eqI1CQNJgCV1shN_gKtV_Z0ZVXK4KybqMurgTSeozb3hz9ZVerMeGhS6ZNnmdLLo_cO4gKUu2E" //TODO Remove

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    single <ApiDataStore> { return@single ApiDataStoreImpl(get()) }
}

private fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val headerInterceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .header("Authorization", "Bearer $TOKEN")
            .build()
        chain.proceed(newRequest)
    }

    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor (headerInterceptor)
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()
}

private fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

