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


class AddSurgeon : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_surgeon)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        sadd.setOnClickListener {

            var id = sid.text.toString()
            var Name = sname.text.toString()
            var phone = sphone.text.toString()
            var email = semail.text.toString()
            var address = sloc.text.toString()

            val docRef = db?.collection("SurgeonDetails")?.document(id!!)

            docRef?.get()?.addOnSuccessListener { documentSnapshot ->
                val surgeonDetails = documentSnapshot.toObject(AddSurgeon::class.java) as Surgeon?
                var u = Surgeon(id, Name, phone,email,address)
                db?.collection("Surgeon")?.document(id!!)?.set(u)
                Toast.makeText(
                    this, "Surgeon added successfully",
                    Toast.LENGTH_LONG
                ).show()

                sid.setText("")
                sname.setText("")
                sphone.setText("")
                semail.setText("")
                sloc.setText("")
            }

        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}



class Surgeon {
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



