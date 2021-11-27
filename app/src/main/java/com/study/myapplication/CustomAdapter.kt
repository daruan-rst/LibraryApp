package com.study.myapplication

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


import android.widget.TextView
import android.content.Intent
import androidx.constraintlayout.widget.ConstraintLayout


internal class CustomAdapter(private val context : Context,
                             private val activity: Activity,
                             private val book_id : ArrayList<*>,
                             private val book_title : ArrayList<String>,
                             private val book_author : ArrayList<String>,
                             private val book_pages : ArrayList<String>   ) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.my_row, parent, false)
        return MyViewHolder(view)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bookIdTxt.text = book_id[position].toString()
        holder.bookTitleTxt.text = book_title[position]
        holder.bookAuthorTxt.text = book_author[position]
        holder.bookPagesTxt.text = book_pages[position]
        holder.mainLayout.setOnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("id", book_id[position].toString())
            intent.putExtra("title", book_title[position])
            intent.putExtra("author", book_author[position])
            intent.putExtra("pages", book_pages[position])
            activity.startActivityForResult(intent, 1)
        }

    }



    override fun getItemCount(): Int {
        return book_id.size
    }

    internal inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bookIdTxt: TextView = itemView.findViewById(R.id.book_id_txt)
        var bookTitleTxt: TextView = itemView.findViewById(R.id.book_title_txt)
        var bookAuthorTxt: TextView = itemView.findViewById(R.id.book_author_txt)
        var bookPagesTxt: TextView = itemView.findViewById(R.id.book_pages_txt)
        val mainLayout :ConstraintLayout = itemView.findViewById(R.id.mainLayout)

        }
}
