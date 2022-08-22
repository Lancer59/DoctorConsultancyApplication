package com.example.doctorconsultancy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class PatientMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_menu)

        val ss:String = intent.getStringExtra("Email").toString()
        var v = findViewById<TextView>(R.id.UserEmailShow)
        v.setText(ss)



    }
    fun gen(v: View?){
        intent = Intent(this,GeneralPage::class.java)
        startActivity(intent)
    }
    fun psy(v: View?){
        intent = Intent(this,PsychiatristPage::class.java)
        startActivity(intent)

    }
    fun sur(v: View?){
        intent = Intent(this,SurgeonPage::class.java)
        startActivity(intent)

    }

}