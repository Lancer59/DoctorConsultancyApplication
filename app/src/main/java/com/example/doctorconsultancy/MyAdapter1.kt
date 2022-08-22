package com.example.doctorconsultancy


import android.content.Intent

import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.doctorconsultancy.MyAdapter1.*
import java.util.*
import kotlin.collections.ArrayList


class MyAdapter1(private val userlist1 : ArrayList<User1>) : RecyclerView.Adapter<MyViewHolder>() ,TextToSpeech.OnInitListener{
    private var tts: TextToSpeech? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.surgeondata, parent, false)

        tts =TextToSpeech(itemView.context,this)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: User1 = userlist1[position]
        holder.id.text = user.id
        holder.name.text = user.name
        holder.phone.text = user.phone
        holder.email.text =user.email
        holder.address.text=user.address



        holder.itemView.findViewById<View>(R.id.bookn2).setOnClickListener(View.OnClickListener() {
            val dialIntent = Intent(Intent.ACTION_DIAL)

            dialIntent.data = Uri.parse("tel:" + holder.phone.text.toString())
            var context=holder.id.context
            context.startActivity(dialIntent)

        });
    //    holder.itemView.findViewById<View>(R.id.texttechnician).setOnClickListener(View.OnClickListener {
    //        tts!!.speak(holder.name.text, TextToSpeech.QUEUE_FLUSH, null,"")
//
  //      })

//email functionality
   //     holder.itemView.findViewById<View>(R.id.button).setOnClickListener(View.OnClickListener {
   //         val intent1 = Intent(Intent.ACTION_MAIN)
  //          intent1.addCategory(Intent.CATEGORY_APP_EMAIL)
  //          var context1=holder.id.context
  //          context1.startActivity(intent1)
            //         startActivity(intent1)

 //       })
        //email functionality
        holder.itemView.findViewById<View>(R.id.button).setOnClickListener(View.OnClickListener {
            val intent1 = Intent(holder.itemView.getContext(), SendMail::class.java)
            var b= Bundle()
            var emailsend= MyAdapter.EmailSend(holder.email.text.toString(), holder.email.text.toString())
            b.putSerializable("EmailSend",emailsend)
            intent1.putExtras(b)
            var context1=holder.id.context
            context1.startActivity(intent1)
        })



    }


    override fun getItemCount(): Int {
        return userlist1.size

    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {


        val name: TextView = itemView.findViewById(R.id.sname)
        val id: TextView = itemView.findViewById(R.id.sid)
        val phone: TextView = itemView.findViewById(R.id.sphone)
        val email:TextView = itemView.findViewById(R.id.semail)
        val address:TextView = itemView.findViewById(R.id.sloc)




    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
        }
    }
}


