package com.hamy.cloudstorage.Utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hamy.cloudstorage.BuildConfig
import com.hamy.cloudstorage.Interface.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providerUrl() = BuildConfig.BaseUrl
    private var interceptor:HttpLoggingInterceptor =  HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY)

    private var client =  OkHttpClient.Builder().addInterceptor(interceptor).build()
    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    fun providerApiService(url:String): ApiService =
        Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)

}