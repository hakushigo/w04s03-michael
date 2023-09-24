package com.example.compostingadv

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Create a notification Channel.
        buildNotChan(getString(R.string.not_chanid_name), NotificationManager.IMPORTANCE_HIGH, "General Notification")
    }

    private fun buildNotChan(
        name: String,
        importance: Int,
        desc : String
    ) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val createdChan = NotificationChannel(
                getString(R.string.not_chanid_id),
                name,
                importance
            ).apply {
                description = desc
            }

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(createdChan)
        }
    }
}