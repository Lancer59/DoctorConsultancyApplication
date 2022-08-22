package com.example.doctorconsultancy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signupactivity.*


class Signupactivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupactivity)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        Continue.setOnClickListener {
            if (checking()) {
                var Email = useremail.text.toString()
                var Password = userpassword.text.toString()



                val user = hashMapOf(

                    "email" to Email
                )
                val Users = db.collection("USERS")
                val query = Users.whereEqualTo("email", Email).get()
                    .addOnSuccessListener { tasks ->
                        if (tasks.isEmpty) {
                            auth.createUserWithEmailAndPassword(Email, Password)
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        Users.document(Email).set(user)
                                        val intent = Intent(this, Loginactivity::class.java)
                                        intent.putExtra("email", Email)
                                        startActivity(intent)

                                    } else {
                                        Toast.makeText(
                                            this,
                                            " Failed",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                        } else {
                            Toast.makeText(this, "User Already Registered", Toast.LENGTH_LONG)
                                .show()
                            val intent = Intent(this, Loginactivity::class.java)
                            startActivity(intent)
                        }
                    }
            } else {
                Toast.makeText(this, "Enter the Details", Toast.LENGTH_LONG).show()
            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun checking(): Boolean {
        if (
             useremail.text.toString().trim { it <= ' ' }.isNotEmpty()
            && userpassword.text.toString().trim { it <= ' ' }.isNotEmpty()
        ) {
            return true
        }
        return false
    }
    fun goToLtogin(v: View?){

        val intent = Intent(applicationContext, Loginactivity::class.java)
        startActivity(intent)
        finish()


    }
}

