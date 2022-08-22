package com.example.doctorconsultancy

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SendMail : AppCompatActivity() {
    lateinit var editTextMail: EditText
    lateinit var editTextSubject: EditText
    lateinit var editTextMessage: EditText
    lateinit var buttonSend: Button
    lateinit var email: String
    lateinit var subject: String
    lateinit var message: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_mail)
        title = "KotlinApp"


        var intent1 = intent
        var emailsend=intent1.getSerializableExtra("EmailSend") as MyAdapter.EmailSend

        var recemail= emailsend.emailshared
        var email1 = recemail

        email = recemail



        var c = findViewById(R.id.editTextMail) as TextView
        c.setText(email1)
        editTextSubject = findViewById(R.id.editTextSubject)
        editTextMessage = findViewById(R.id.editTextMessage)
        subject = editTextSubject.text.toString()
        message = editTextMessage.text.toString()
        buttonSend = findViewById(R.id.buttonSend)
        buttonSend.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type="text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, email)
            intent.putExtra(Intent.EXTRA_CC, email)
            intent.putExtra(Intent.EXTRA_BCC, email)
            intent.putExtra(Intent.EXTRA_SUBJECT, message)
            intent.putExtra(Intent.EXTRA_TEXT, message)

         //   val intent = Intent(Intent.ACTION_SENDTO)
        //        intent.data = Uri.parse("mailto:")
//
  //              intent.putExtra(Intent.EXTRA_EMAIL, email)
    //            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
      //          intent.putExtra(Intent.EXTRA_TEXT, message)

        //    startActivity(Intent.createChooser(intent, "Select email"))


                startActivity(intent)

    }
}


}