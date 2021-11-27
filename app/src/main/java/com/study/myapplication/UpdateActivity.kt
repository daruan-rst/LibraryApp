package com.study.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_add2.*
import android.widget.Toast


import android.util.Log
import com.study.myapplication.databinding.ActivityUpdateBinding


class UpdateActivity : AppCompatActivity() {

    val binding by lazy{
        ActivityUpdateBinding.inflate(layoutInflater)
    }

    var pages : String? = ""
    var id : String = ""
    var title : String? = ""
    var author : String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val titleInput :EditText = binding.titleInputUpdate
        val authorInput :EditText = binding.authorInputUpdate
        val pagesInput :EditText = binding.pagesInputUpdate
        val update_button : Button = binding.updateButton


        getAndSetIntentData()

        update_button.setOnClickListener { //And only then we call this
            val myDB = DatabaseHelper(this@UpdateActivity)
            title = titleInput.text.toString().trim { it <= ' ' }
            author = authorInput.text.toString().trim { it <= ' ' }
            pages = pagesInput.text.toString().trim { it <= ' ' }
            myDB.updateData(id, title, author, pages)
        }


    }

    fun getAndSetIntentData() {
        if (intent.hasExtra("id") && intent.hasExtra("title") &&
            intent.hasExtra("author") && intent.hasExtra("pages")
        ) {

            val titleInput :EditText = binding.titleInputUpdate
            val authorInput :EditText = binding.authorInputUpdate
            val pagesInput :EditText = binding.pagesInputUpdate

            id = intent.getStringExtra("id")!!
            title = intent.getStringExtra("title")
            author = intent.getStringExtra("author")
            pages = intent.getStringExtra("pages")

            titleInput.setText(title)
            authorInput.setText(author)
            pagesInput.setText(pages)
            Log.d("stev", title.toString() + " " + author + " " + pages)
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show()
        }
    }
}