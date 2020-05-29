package com.sysaxiom.jobscheduler.startjob

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sysaxiom.jobscheduler.R
import kotlinx.android.synthetic.main.activity_start_job.*

class StartJobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_job)

        start_job.setOnClickListener {
            val componentName = ComponentName(this, StartJobService::class.java)
            var builder = JobInfo.Builder(102, componentName)
                .setPersisted(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build()

            val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.schedule(builder)
        }

    }
}
