package com.example.doctorconsultancy

import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase


private lateinit var recyclerView: RecyclerView
private lateinit var userArrayList: ArrayList<User>

private lateinit var myAAdapter: MyAAdapter
private lateinit var db: FirebaseFirestore



class APsychiatristPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apsychiatrist_page)
        recyclerView = findViewById(R.id.rvp)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()

        myAAdapter = MyAAdapter(userArrayList)
        recyclerView.adapter = myAAdapter



        EventChangeListener()



    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun EventChangeListener(){
        db = FirebaseFirestore.getInstance()
        db.collection("Psychiatrist").
        addSnapshotListener(object :EventListener<QuerySnapshot>{
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if(error!=null){

                    Log.e("firestore error",error.message.toString())
                    return
                }
                for(dc:DocumentChange in value?.documentChanges!!){

                    if(dc.type ==DocumentChange.Type.ADDED){
                        userArrayList.add(dc.document.toObject(User::class.java))
                    }
                }
                myAAdapter.notifyDataSetChanged()
            }
        })




    }



}