package com.example.doctorconsultancy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_loginactivity.*

class Loginactivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        adlogin.setOnClickListener {
            if (checking()) {
                val Email = aemail.text.toString()
                val Password = apassword.text.toString()
                val Users = db.collection("USERS")
                val query = Users.whereEqualTo("Email", Email).get()
                    .addOnSuccessListener { tasks ->
                        if (tasks.isEmpty) {
                            auth.signInWithEmailAndPassword(Email, Password)
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        var intent = Intent(this, PatientMenu::class.java)
                                        intent.putExtra("Email", Email)
                                        startActivity(intent)

                                    } else {
                                        Toast.makeText(this, "Wrong Details", Toast.LENGTH_LONG)
                                            .show()
                                    }
                                }
                        } else {
                            Toast.makeText(this, "empty", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            }
            else {
                Toast.makeText(this, "Enter the Details", Toast.LENGTH_LONG).show()

            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun checking(): Boolean {
        if (aemail.text.toString().trim { it <= ' ' }.isNotEmpty()
            && apassword.text.toString().trim { it <= ' ' }.isNotEmpty()
        ) {
            return true
        }
        return false
    }
    fun gotologin(v: View?) {
        var intent1 = Intent(this, Signupactivity::class.java)
            startActivity(intent1)
    }
}



