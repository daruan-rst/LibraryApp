package com.study.myapplication


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class DatabaseHelper(
    private val context: Context?,
    DATABASE_NAME: String? = "BookLibrary.db",
    VERSION: Int = 1
) : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {





    override fun onCreate(db: SQLiteDatabase?) {
        var query : String = "CREATE TABLE " + TABLE_NAME +
                            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            COLUMN_TITLE + " TEXT, " +
                            COLUMN_AUTHOR + " TEXT, " +
                            COLUMN_PAGES + " INTEGER);"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, i: Int, j: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    open fun addBook(title : String, author:String, pages:Int){
        val db : SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_AUTHOR, author)
        cv.put(COLUMN_PAGES, pages)
        var result : Long = db.insert(TABLE_NAME, null, cv)

        if (result == -1L){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

        }else {
        Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
    }
    }



    fun readAllData(): Cursor {
        val query = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        return db.rawQuery(query, null)

    }

    fun updateData(row_id: String, title: String?, author: String?, pages: String?) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_AUTHOR, author)
        cv.put(COLUMN_PAGES, pages)
        val result = db.update(TABLE_NAME, cv, "_id=?", arrayOf(row_id)).toLong()
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show()
        }
    }



    companion object {
        private const val TABLE_NAME = "my_library"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_TITLE = "book_title"
        private const val COLUMN_AUTHOR = "book_author"
        private const val COLUMN_PAGES = "book_pages"
    }

}