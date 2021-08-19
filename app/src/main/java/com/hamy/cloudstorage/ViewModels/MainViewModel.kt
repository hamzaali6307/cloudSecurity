package com.hamy.cloudstorage.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamy.cloudstorage.Model.Data
import com.hamy.cloudstorage.Repository.MainRepositry
import com.hamy.cloudstorage.Utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(val mainRepositry: MainRepositry) : ViewModel() {
     private  val postStateFlow: MutableStateFlow<ApiState> =
         MutableStateFlow(ApiState.Empty)

     val myPostStateFlow: StateFlow<ApiState> = postStateFlow

    fun getLogin( email:String,passwword:String) = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        mainRepositry.getLoginDetails(email,passwword)
            .catch { e ->
                postStateFlow.value = ApiState.Failure(e)
            }.collect { data ->
                postStateFlow.value = ApiState.Success(data)
            }
    }
}