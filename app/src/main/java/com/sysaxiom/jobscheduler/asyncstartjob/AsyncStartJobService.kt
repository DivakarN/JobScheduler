package com.sysaxiom.jobscheduler.asyncstartjob

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import androidx.core.app.NotificationCompat

class AsyncStartJobService : JobService() {

    private var jobCancelled = false

    override fun onStartJob(p0: JobParameters?): Boolean {
        p0?.let { doBackgroundWork(it) }
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        jobCancelled = true
        return true
    }

    private fun doBackgroundWork(params: JobParameters) {
        Thread(Runnable {
            for (i in 0..9) {
                println("run: $i")
                if (jobCancelled) {
                    return@Runnable
                }
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            displayNotification()
            jobFinished(params, false)
        }).start()
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
            .setSmallIcon(com.sysaxiom.jobscheduler.R.drawable.ic_android)

        notificationManager.notify(1, notification.build())
    }

}