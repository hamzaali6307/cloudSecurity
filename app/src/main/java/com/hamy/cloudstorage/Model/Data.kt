package com.hamy.cloudstorage.Model

data class Data(
    val success: Boolean, val user_name: String,
    val data: ArrayList<Data>,
    val image: String,
    val contact: String,
    val password:String,
    val file_type: String,
    val file_password:String,
    val added_by: String,
    val time:String,
    val file_name :String,
    val file_link:String,
    val email: String,
    val id: String,
    val code: String,
    val message: String
)
