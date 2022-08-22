package com.example.doctorconsultancy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class AdminView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_view)
    }
    fun Gen(v: View?){
        intent = Intent(this,AGeneralPage::class.java)
        startActivity(intent)
    }
    fun Psy(v: View?){
        intent = Intent(this,APsychiatristPage::class.java)
        startActivity(intent)
    }
    fun Sur(v: View?){
        intent = Intent(this,ASurgeonPage::class.java)
        startActivity(intent)
    }
}
