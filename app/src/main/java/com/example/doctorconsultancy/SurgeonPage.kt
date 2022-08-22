package com.example.doctorconsultancy


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*



private lateinit var recyclerView: RecyclerView
private lateinit var userArrayList1: ArrayList<User1>

private lateinit var myAdapter1: MyAdapter1
private lateinit var db: FirebaseFirestore



class SurgeonPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surgeon_page)
        recyclerView = findViewById(R.id.recyclerViews)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        userArrayList1 = arrayListOf()

        myAdapter1 = MyAdapter1(userArrayList1)
        recyclerView.adapter = myAdapter1



        EventChangeListener()



    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun EventChangeListener(){
        db = FirebaseFirestore.getInstance()
        db.collection("Surgeon").
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
                        userArrayList1.add(dc.document.toObject(User1::class.java))
                    }
                }
                myAdapter1.notifyDataSetChanged()
            }
        })




    }



}