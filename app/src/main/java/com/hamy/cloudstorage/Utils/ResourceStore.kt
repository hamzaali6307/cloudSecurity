package com.hamy.cloudstorage.Utils

import com.hamy.cloudstorage.Fragments.FileView
import com.hamy.cloudstorage.R

interface ResourceStore {
    companion object {
        val tabList = listOfNotNull(
            "Image",
            "Video",
            "Audio",
            "File"
        )
        val pagerFragments = listOf(
            FileView.create("Image"), FileView.create("Video"),
            FileView.create("Audio"), FileView.create("File"))
    }
}