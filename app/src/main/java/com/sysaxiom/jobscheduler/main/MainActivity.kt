package com.sysaxiom.jobscheduler.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sysaxiom.jobscheduler.R
import com.sysaxiom.jobscheduler.asyncstartjob.AsyncStartJobActivity
import com.sysaxiom.jobscheduler.specifictime.StartJobAtSpecificTimeActivity
import com.sysaxiom.jobscheduler.startjob.StartJobActivity
import com.sysaxiom.jobscheduler.startrepeatingjob.StartRepeatingJobActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_job.setOnClickListener {
            Intent(this, StartJobActivity::class.java).also {
                this.startActivity(it)
            }
        }

        start_repeating_job.setOnClickListener {
            Intent(this, StartRepeatingJobActivity::class.java).also {
                this.startActivity(it)
            }
        }

        start_job_at_specific_time.setOnClickListener {
            Intent(this, StartJobAtSpecificTimeActivity::class.java).also {
                this.startActivity(it)
            }
        }

        start_async_job.setOnClickListener {
            Intent(this, AsyncStartJobActivity::class.java).also {
                this.startActivity(it)
            }
        }
    }

}
