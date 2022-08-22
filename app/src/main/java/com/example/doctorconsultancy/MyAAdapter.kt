package com.example.doctorconsultancy


import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorconsultancy.MyAdapter.*
import com.google.firebase.firestore.FirebaseFirestore
import java.io.Serializable
import java.util.*

class MyAAdapter(private val userlist : ArrayList<User>) : RecyclerView.Adapter<MyAAdapter.MyViewHolder3>(),TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder3 {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.apsychiatristdata, parent, false)

        tts =TextToSpeech(itemView.context,this)
        return MyViewHolder3(itemView)
    }



    override fun onBindViewHolder(holder: MyViewHolder3, position: Int) {
        val user: User = userlist[position]
        holder.id.text = user.id
        holder.name.text = user.name
        holder.phone.text = user.phone
        holder.email.text =user.email
        holder.address.text=user.address


        holder.itemView.findViewById<View>(R.id.bookn2).setOnClickListener(View.OnClickListener() {


            var db: FirebaseFirestore = FirebaseFirestore.getInstance()
            val id = holder.id.text
            db.collection("Psychiatrist").document(id.toString())
                .delete()
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
            Toast.makeText(holder.itemView.getContext(),"Delete Successful", Toast.LENGTH_LONG).show()
            val intent1 = Intent(holder.itemView.getContext(), APsychiatristPage::class.java)
            var context1=holder.id.context
            context1.startActivity(intent1)
        });
        //    holder.itemView.findViewById<View>(R.id.textelectrician).setOnClickListener(View.OnClickListener {


        //       tts!!.speak(holder.name.text, TextToSpeech.QUEUE_FLUSH, null,"")

        //    })

        //email functionality





    }

    data class EmailSend(val emailshared:String, val hi:String): Serializable


    override fun getItemCount(): Int {
        return userlist.size

    }


    inner class MyViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        val name: TextView = itemView.findViewById(R.id.pname)
        val id: TextView = itemView.findViewById(R.id.pid)
        val phone: TextView = itemView.findViewById(R.id.pphone)
        val email:TextView = itemView.findViewById(R.id.pemail)
        val address:TextView = itemView.findViewById(R.id.ploc)

    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
        }
    }

}


