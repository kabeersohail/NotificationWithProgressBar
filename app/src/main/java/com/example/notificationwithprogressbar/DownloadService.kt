package com.example.notificationwithprogressbar

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class DownloadService(private val context: Context) {

    private val notificationId = 1
    private val notificationChannelId = "download_channel"
    private val notificationManager: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(context)
    }

    private lateinit var notificationBuilder: NotificationCompat.Builder

    fun startDownload() {
        createNotificationChannel()

        notificationBuilder = NotificationCompat.Builder(context, notificationChannelId)
            .setContentTitle("Downloading...")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setProgress(100, 0, false)
            .setOngoing(true)

        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    fun stopDownload() {
        notificationManager.cancel(notificationId)
    }

    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val name = "Download"
            val description = "Download progress notifications"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(notificationChannelId, name, importance)
            channel.description = description
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun updateProgress(newProgress: Int) {
        notificationBuilder.setProgress(100, newProgress, false)
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

}
