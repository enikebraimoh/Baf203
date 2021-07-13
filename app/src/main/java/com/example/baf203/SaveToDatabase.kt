package com.example.baf203

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class SaveToDatabase(val data : ArrayList<SmsModel>) {

    private val database: DatabaseReference by lazy {
        Firebase.database.reference
    }

   fun save (){
       Log.d("testing",data.toString())
       for (model in data) {
           database.child("Messages").child(model.id)
               .setValue(model).addOnCompleteListener {
                   Log.d("testing", "saved to firebase inbox")
               }
       }
   }

}