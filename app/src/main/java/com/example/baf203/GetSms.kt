package com.example.baf203

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.util.Log


class GetSms(val context : Context) {
    var list = ArrayList<SmsModel>()

    var cr: ContentResolver = context.contentResolver

    val cursor : Cursor = context.contentResolver.query(
        Uri.parse("content://sms"), null,null,null,null)!!

    fun getMessages() {
        Log.d("testing", "in get message function")
        while (cursor.moveToNext()) {
            val model = SmsModel(
                cursor.getString(cursor.getColumnIndexOrThrow("_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("address")),
                cursor.getString(cursor.getColumnIndexOrThrow("body")))
            list.add(model)
            Log.d("testing", model.body)
            cursor.moveToNext()
        }
        SaveToDatabase(list).save()
    }

}


