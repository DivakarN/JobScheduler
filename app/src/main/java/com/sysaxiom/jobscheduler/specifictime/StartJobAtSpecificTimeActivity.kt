package com.sysaxiom.jobscheduler.specifictime

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sysaxiom.jobscheduler.R
import kotlinx.android.synthetic.main.activity_start_job_at_specific_time.*

class StartJobAtSpecificTimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_job_at_specific_time)

        start_job.setOnClickListener {
            val componentName = ComponentName(this, StartJobAtSpecificTimeService::class.java)
            var builder = JobInfo.Builder(101, componentName)
            builder.setMinimumLatency(60000)
            builder.setPersisted(true)
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)

            val jobInfo = builder.build()
            val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.schedule(jobInfo)
        }
    }
}
