package com.bakharaalief.graphqlappic.data.network.retrofit

import com.bakharaalief.graphqlappic.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiConfig {

    private const val url = BuildConfig.staggingURLAuth

    fun getApiService(): ApiService {

        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val basicInterceptor = Interceptor { chain ->
            val request = chain.request()
            val authenticatedRequest = request.newBuilder()
                .addHeader(
                    "Authorization",
                    Credentials.basic(BuildConfig.username, BuildConfig.password)
                )
                .build()
            chain.proceed(authenticatedRequest)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(basicInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(ApiService::class.java)
    }
}