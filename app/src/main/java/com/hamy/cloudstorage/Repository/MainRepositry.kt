package com.hamy.cloudstorage.Repository

import com.hamy.cloudstorage.Interface.ApiServiceProvider
import com.hamy.cloudstorage.Model.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.Flow
import javax.inject.Inject

class MainRepositry @Inject constructor(private val apiServiceProvider: ApiServiceProvider){

    fun getLoginDetails(email:String,password:String) = flow {
        emit(apiServiceProvider.userLogin(email,password))
    }.flowOn(Dispatchers.IO)


}
