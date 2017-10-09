package com.android.course.pushnotificationtest.pushnotification

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import android.util.Log

/**
 * Created by paolafalcomata on 04/10/2017.
 */
class MyJobService : JobService() {

    companion object {
        val TAG: String = MyJobService::class.java.simpleName
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d(TAG, "Performing long running task in scheduled job")
        // TODO(developer): add long running task here.
        return false

    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        return false
    }
}