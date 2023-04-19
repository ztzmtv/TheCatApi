package com.azmetov.thecatapi.presentation.imageloader

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

interface Downloader {
    fun downloadFile(url: String): Long
}

class ImageDownloader(context: Context) : Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpeg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("image.jpg")
            .addRequestHeader("Authorization", "Bearer <token>")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")
        return downloadManager.enqueue(request)
    }

}