# JobScheduler

JobScheduler:
-------------

It was introduced in Lollipop
It performs work based on conditions, not on time
JobScheduler is guaranteed to get your job done, but since it operates at the system level, it can also use several factors to intelligently schedule your background work to run with the jobs from other apps.

JobService:
-----------

JobService is service which enables the system to perform your work regardless of whether your app is active.
JobService will run on the main thread
JobService class should be registered in manifest file.
It has two methods,

onStartJob():
------------

onStartJob() is called by the system when it is time for your job to execute.
If your task is short and simple, feel free to implement the logic directly in onStartJob() and return false when you are finished, to let the system know that all work has been completed.
But if you need to do a more complicated task, like connecting to the network, you’ll want to kick off a background thread and return true, letting the system know that you have a thread still running and it should hold on to your wakelock for a while longer.

onStopJob():
------------

onStopJob() is called by the system if the job is cancelled before being finished.
This generally happens when your job conditions are no longer being met, such as when the device has been unplugged or if WiFi is no longer available.
Return true if you’d like the system to reschedule the job, or false if it doesn’t matter and the system will drop this job.


jobFinished(jobParameter, true/false):
-------------------------------------

It should be called once your service or thread has finished working on the job.
jobFinished() requires two parameters: the current job, so that it knows which wakelock can be released, and a boolean indicating whether you’d like to reschedule the job.
If you pass in true, this will kick off the JobScheduler’s exponential backoff logic for you.

class StartJobService : JobService() {

    override fun onStartJob(p0: JobParameters?): Boolean {
        //work
        return false // If Job is not finished or running in other threads then return true otherwise return false.
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return false // If we do need to reschedule then return true otherwise return false.
    }

}

Adding in AndroidManifest.xml:
------------------------------

It will allow JobScheduler to call your jobs.

<service
  android:name=".StartJobService"
  android:permission="android.permission.BIND_JOB_SERVICE"
  android:exported="true"/>

JobInfo:
-------

JobInfo requires two parameter 1) JobId and 2) JobService
It adds some constraint in work so that it will execute at a specific time.

JobScheduler have many constraints available for example,

1) Network type (metered/unmetered) - If your job requires network access, you must include this condition.
2) Charging - If you job requires to be run in charging condition, then you can use this condition.
3) Idle - If you job requires to be run even on idle mode, then you can use this condition.
4) Minimum latency - It delays the job at a interval of 'X' millisecond.
5) Periodic - It repeats the job at every 'X' TimeUnit. Job runs at regular 15 mins + interval.
6) Persistent - It persisted the jobs across a reboot. But it needs RECEIVE_BOOT_COMPLETED permission.
7) Extras - If your job needs some information from your app to perform its work, you can pass along primitive data types as extras in the JobInfo.

val componentName = ComponentName(this, StartJobService::class.java)
var builder = JobInfo.Builder(101, componentName)
    .setPersisted(true)
    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
    .build()

JobScheduler:
-------------

Its schedules the jobs.

val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
jobScheduler.schedule(builder)
