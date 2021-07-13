package com.example.baf203

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class Baf203Application : Application() {

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.Default).launch {
            val constraints = Constraints.Builder()
                .setRequiresBatteryNotLow(true)
               .build()
            val repeatingRequest =
                PeriodicWorkRequestBuilder<RefreshData>(16, TimeUnit.MINUTES)
                    .setConstraints(constraints)
                    .build()

            WorkManager.getInstance(applicationContext)
                .enqueueUniquePeriodicWork(RefreshData.WORK_NAME,
                    ExistingPeriodicWorkPolicy.REPLACE, repeatingRequest)
        }
    }


}