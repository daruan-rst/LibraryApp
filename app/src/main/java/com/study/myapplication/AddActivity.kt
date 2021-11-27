package com.study.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add2)

        val titleInput : EditText = findViewById(R.id.title_input_update)
        val authorInput :EditText = findViewById(R.id.author_input_update)
        val pagesInput : EditText = findViewById(R.id.pages_input_update)
        val addButton : Button = findViewById(R.id.update_button)
        addButton.setOnClickListener {
            val myDB = DatabaseHelper(this@AddActivity)
            myDB.addBook(
                titleInput.text.toString().trim { it <= ' ' },
                authorInput.text.toString().trim { it <= ' ' },
                Integer.valueOf(pagesInput.text.toString().trim { it <= ' ' })
            )
        }
    }
}