package com.hamy.cloudstorage.Utils

import com.hamy.cloudstorage.BuildConfig
import com.hamy.cloudstorage.Interface.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providerUrl() = BuildConfig.BaseUrl

    @Provides
    fun providerApiService(url:String): ApiService =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

}