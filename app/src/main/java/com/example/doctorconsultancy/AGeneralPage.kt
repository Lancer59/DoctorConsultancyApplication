package com.example.doctorconsultancy


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*



private lateinit var recyclerView: RecyclerView
private lateinit var userArrayList2: ArrayList<User2>

private lateinit var myAAdapter2: MyAAdapter2
private lateinit var db: FirebaseFirestore



class AGeneralPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ageneral_page)
        recyclerView = findViewById(R.id.rvg)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        userArrayList2 = arrayListOf()

        myAAdapter2 = MyAAdapter2(userArrayList2)
        recyclerView.adapter = myAAdapter2



        EventChangeListener()




    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("General").addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if (error != null) {

                    Log.e("firestore error", error.message.toString())
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!) {

                    if (dc.type == DocumentChange.Type.ADDED) {
                        userArrayList2.add(dc.document.toObject(User2::class.java))
                    }
                }
                myAAdapter2.notifyDataSetChanged()
            }
        })
    }






}




