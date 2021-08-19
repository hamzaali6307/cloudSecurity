package com.hamy.cloudstorage.Utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

object Notifier {

    fun showSnackBar(view:View,message:String){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()

    }
}