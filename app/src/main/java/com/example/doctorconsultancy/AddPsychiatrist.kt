package com.example.doctorconsultancy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_add_psychiatrist.*

import kotlinx.android.synthetic.main.activity_add_surgeon.*
import kotlinx.android.synthetic.main.activity_add_general.*


class AddPsychiatrist : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_psychiatrist)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        padd.setOnClickListener {

            var id = pid.text.toString()
            var Name = pname.text.toString()
            var phone = pphone.text.toString()
            var email = pemail.text.toString()
            var address = ploc.text.toString()

            val docRef = db?.collection("PsychiatristDetails")?.document(id!!)

            docRef?.get()?.addOnSuccessListener { documentSnapshot ->
                val psychiatristDetails = documentSnapshot.toObject(AddPsychiatrist::class.java) as Psychiatrist?
                var u = Psychiatrist(id, Name, phone,email,address)
                db?.collection("Psychiatrist")?.document(id!!)?.set(u)
                Toast.makeText(
                    this, "Psychiatrist added successfully",
                    Toast.LENGTH_LONG
                ).show()

                pid.setText("")
                pname.setText("")
                pphone.setText("")
                pemail.setText("")
                ploc.setText("")
            }

        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}



class Psychiatrist {
    var id=""
    var Name=""
    var phone=""
    var email=""
    var address=""

    constructor(id:String,Name:String,phone:String,email:String,address:String)
    {
        this.id=id
        this.Name=Name
        this.phone=phone
        this.email=email
        this.address=address

    }
    constructor()


}



