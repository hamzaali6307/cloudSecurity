package com.hamy.cloudstorage.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("success")
    @Expose
    val success: Boolean,
    @SerializedName("user_name")
    @Expose
    val user_name: String,
    @SerializedName("data")
    @Expose
    val data: ArrayList<Data>,
    @SerializedName("image")
    @Expose
    val image: String,
    @SerializedName("contact")
    @Expose
    val contact: String,
    @SerializedName("password")
    @Expose
    val password:String,
    @SerializedName("file_type")
    @Expose
    val file_type: String,
    @SerializedName("file_password")
    @Expose
    val file_password:String,
    @SerializedName("added_by")
    @Expose
    val added_by: String,
    @SerializedName("time")
    @Expose
    val time:String,
    @SerializedName("file_name")
    @Expose
    val file_name :String,
    @SerializedName("file_link")
    @Expose
    val file_link:String,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("code")
    @Expose
    val code: String,
    @SerializedName("message")
    @Expose
    val message: String
)
