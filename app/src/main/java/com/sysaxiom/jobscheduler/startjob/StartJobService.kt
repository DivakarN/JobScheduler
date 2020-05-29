package com.sysaxiom.jobscheduler.startjob

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.sysaxiom.jobscheduler.R

class StartJobService : JobService() {

    override fun onStartJob(p0: JobParameters?): Boolean {
        displayNotification()
        return false
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return false
    }

    private fun displayNotification() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channelID",
                "Channel Name",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, "channelID")
            .setContentTitle("My Job")
            .setContentText("The task data passed from StartRepeatingJobActivity")
            .setSmallIcon(R.drawable.ic_android)

        notificationManager.notify(1, notification.build())
    }

}