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


class AddGeneral : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_general)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        gadd.setOnClickListener {

            var id = gid.text.toString()
            var Name = gname.text.toString()
            var phone = gphone.text.toString()
            var email = gemail.text.toString()
            var address = gloc.text.toString()

            val docRef = db?.collection("GeneralDetails")?.document(id!!)

            docRef?.get()?.addOnSuccessListener { documentSnapshot ->
                val generalDetails = documentSnapshot.toObject(AddGeneral::class.java) as General?
                var u = Surgeon(id, Name, phone,email,address)
                db?.collection("General")?.document(id!!)?.set(u)
                Toast.makeText(
                    this, "General Doctor added successfully",
                    Toast.LENGTH_LONG
                ).show()

                gid.setText("")
                gname.setText("")
                gphone.setText("")
                gemail.setText("")
                gloc.setText("")
            }

        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}



class General {
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



