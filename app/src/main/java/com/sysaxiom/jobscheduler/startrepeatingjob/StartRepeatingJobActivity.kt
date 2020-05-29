package com.sysaxiom.jobscheduler.startrepeatingjob

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sysaxiom.jobscheduler.R
import com.sysaxiom.jobscheduler.startjob.StartJobService
import kotlinx.android.synthetic.main.activity_start_repeating_job.*

class StartRepeatingJobActivity : AppCompatActivity() {

    var jobId =103

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_repeating_job)

        start_job.setOnClickListener {
            Toast.makeText(this,"Job Started",Toast.LENGTH_LONG).show()
            val componentName = ComponentName(this, StartRepeatingJobService::class.java)

            val builder = JobInfo.Builder(jobId, componentName)
            builder.setPersisted(true)
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            builder.setPeriodic(30000)

            val jobInfo = builder.build()
            val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.schedule(jobInfo)
        }

        cancel_job.setOnClickListener {
            Toast.makeText(this,"Job Cancelled",Toast.LENGTH_LONG).show()
            val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.cancel(jobId)
        }

    }
}
