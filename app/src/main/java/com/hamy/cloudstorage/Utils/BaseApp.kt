package com.hamy.cloudstorage.Utils

import android.app.Application
import com.hamy.cloudstorage.BuildConfig
import com.hamy.cloudstorage.Interface.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class BaseApp : Application() {



}