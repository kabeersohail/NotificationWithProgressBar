package com.example.notificationwithprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a DownloadService object and start the download
        val downloadService = DownloadService(this)
        downloadService.startDownload()

        // Wait for the download to complete (you can replace this with your own logic)
        for (i in 1..100) {
            Thread.sleep(600)
            // Update the progress of the download service
            downloadService.updateProgress(i)
        }

        // Stop the download and remove the notification
        downloadService.stopDownload()



    }
}