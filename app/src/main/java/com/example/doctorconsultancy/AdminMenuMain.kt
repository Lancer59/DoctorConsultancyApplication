package com.example.doctorconsultancy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AdminMenuMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu_main)
    }
    fun view(v: View?){
        intent = Intent(this,AdminView::class.java)
        startActivity(intent)
    }
    fun add(v: View?){
        intent = Intent(this,AdminMenu::class.java)
        startActivity(intent)
    }
}