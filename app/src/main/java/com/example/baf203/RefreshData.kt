package com.example.baf203

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class RefreshData(val appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object{
        const val WORK_NAME = "RefreshData"
    }

    override suspend fun doWork(): Result {

        val sms = GetSms(appContext)
        return try {
            //get messages
            sms.getMessages()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }

    }

}
