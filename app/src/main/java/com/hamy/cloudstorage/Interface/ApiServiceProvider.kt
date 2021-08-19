package com.hamy.cloudstorage.Interface

import com.hamy.cloudstorage.Model.Data
import retrofit2.Call
import java.util.ArrayList
import javax.inject.Inject

class ApiServiceProvider @Inject constructor(private val api: ApiService){

    suspend fun userLogin( email:String, password:String) : ArrayList<Data> = api.sendLoginData(email,password )
}