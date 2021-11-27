package com.study.myapplication

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager







class MainActivity : AppCompatActivity(R.layout.activity_main) {


    var empty_imageview: ImageView? = null
    var no_data: TextView? = null

    var db: DatabaseHelper = DatabaseHelper(this)



    val bookId = ArrayList<String>()
    val bookTitle = ArrayList<String>()
    val bookAuthor = ArrayList<String>()
    val bookPages = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        val addButton : FloatingActionButton = findViewById(R.id.floatingActionButton)
//        db  = DatabaseHelper(this)




        addButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        }


        storeDataInArrays()

        var customAdapter : CustomAdapter = CustomAdapter(
            this@MainActivity,this, bookId, bookTitle, bookAuthor,
            bookPages
        )
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

    }

    fun storeDataInArrays() {

        val cursor: Cursor = this.db.readAllData()
        if (cursor.count == 0) {
            empty_imageview!!.visibility = VISIBLE
            no_data!!.visibility = VISIBLE
        } else {
            while (cursor.moveToNext()) {


                bookId.add(cursor.getString(0))
                bookTitle.add(cursor.getString(1))
                bookAuthor.add(cursor.getString(2))
                bookPages.add(cursor.getString(3))
            }
//            empty_imageview!!.visibility = View.GONE
//            no_data!!.visibility = View.GONE
        }
    }


}