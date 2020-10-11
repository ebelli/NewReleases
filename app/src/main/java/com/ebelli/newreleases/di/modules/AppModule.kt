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
private const val TOKEN = "BQCt3_rSQFIgYKqEsxUljvLmcV-ezY_xBShsR27Wv92LpzEOqnowpG_2wVRkbSrdCvKKZYx7bXLLSDqsVfRnlylf8hvQ0A9siIRDRLgPPqWFG9ElJFqurS45WNOB2E0XTaThfGZxpVAjNA5n0B8" //TODO Remove

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
