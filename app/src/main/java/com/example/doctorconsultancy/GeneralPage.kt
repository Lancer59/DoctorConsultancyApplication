package com.example.doctorconsultancy


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*



private lateinit var recyclerView: RecyclerView
private lateinit var userArrayList2: ArrayList<User2>

private lateinit var myAdapter2: MyAdapter2
private lateinit var db: FirebaseFirestore



class GeneralPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_page)
        recyclerView = findViewById(R.id.recyclerViewg)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        userArrayList2 = arrayListOf()

        myAdapter2 = MyAdapter2(userArrayList2)
        recyclerView.adapter = myAdapter2



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
                myAdapter2.notifyDataSetChanged()
            }
        })
    }






}




