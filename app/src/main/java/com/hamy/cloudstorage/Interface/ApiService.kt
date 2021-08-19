package com.hamy.cloudstorage.Interface

import com.hamy.cloudstorage.Model.Data
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.ArrayList

interface ApiService {
    @FormUrlEncoded
    @POST("login.php")
    suspend fun sendLoginData(
        @Field("email") email: String?,
        @Field("password") password: String?): ArrayList<Data>


}