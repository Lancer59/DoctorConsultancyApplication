package com.example.doctorconsultancy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class AdminMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu)
    }
    fun Gen(v: View?){
        intent = Intent(this,AddGeneral::class.java)
        startActivity(intent)
    }
    fun Psy(v: View?){
        intent = Intent(this,AddPsychiatrist::class.java)
        startActivity(intent)
    }
    fun Sur(v: View?){
        intent = Intent(this,AddSurgeon::class.java)
        startActivity(intent)
    }
}
