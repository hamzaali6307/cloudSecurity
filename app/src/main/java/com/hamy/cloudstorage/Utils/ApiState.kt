package com.hamy.cloudstorage.Utils

import com.hamy.cloudstorage.Model.Data
import retrofit2.Call
import java.util.ArrayList

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    class Success(val data: ArrayList<Data>): ApiState()
    object Empty : ApiState()





}
