package com.sysaxiom.jobscheduler.asyncstartjob

import android.app.job.JobScheduler
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_async_start_job.*
import android.app.job.JobInfo
import android.content.ComponentName

class AsyncStartJobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sysaxiom.jobscheduler.R.layout.activity_async_start_job)

        start_job.setOnClickListener {
            val componentName = ComponentName(this, AsyncStartJobService::class.java)
            val info = JobInfo.Builder(104, componentName)
                .setPersisted(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build()

            val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.schedule(info)
        }

        cancel_job.setOnClickListener {
            val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.cancel(104)
        }

    }
}
