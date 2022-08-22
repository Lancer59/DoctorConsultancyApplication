package com.example.doctorconsultancy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast


class AdminLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun adminlogin(v: View?)
    {
        var u=findViewById<View>(R.id.aemail) as EditText
        var p=findViewById<View>(R.id.apassword) as EditText
        if(u.text.toString()=="admin" && p.text.toString()=="admin") {
            Toast.makeText(this,"Login success",Toast.LENGTH_LONG).show()
            var i= Intent(applicationContext,AdminMenuMain::class.java)
            startActivity(i)
        }
        else {
            Toast.makeText(this,"Invalid Admin Credentials",Toast.LENGTH_LONG).show()
        }
    }
}