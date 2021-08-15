package com.hamy.cloudstorage.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.contextaware.withContextAvailable
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hamy.cloudstorage.R
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule

@DelicateCoroutinesApi
class SplashView : Fragment() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        activity?.runOnUiThread {
//            Timer("Splash", false).schedule(500) {
//                navController.navigate(R.id.splash_to_login)
//            }
            Handler().postDelayed({
                navController.navigate(R.id.splash_to_login)
            }, 1000)
        }

    }

}