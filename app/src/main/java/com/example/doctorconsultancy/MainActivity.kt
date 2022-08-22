package com.example.doctorconsultancy
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }



    fun usersignup(v: View?) {

        var intent = Intent(this, Loginactivity::class.java)
        startActivity(intent)


    }

    fun adminlogin(v: View?) {

        var intent = Intent(this, AdminLogin::class.java)
        startActivity(intent)


    }
}
