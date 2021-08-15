package com.hamy.cloudstorage.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.hamy.cloudstorage.R

class FileView : Fragment() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fileview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {
        fun create(viewType:String): FileView {
            var viewData = FileView()
            when(viewType){
                "Image" ->{
                    viewData = FileView()
                }
                "Video" ->{
                    viewData = FileView()
                }
                "Audio" ->{
                    viewData = FileView()
                }
                "File" ->{
                    viewData = FileView()
                }
            }
            return viewData
        }
    }


}