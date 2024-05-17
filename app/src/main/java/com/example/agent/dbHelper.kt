package com.example.agent

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                login TEXT NOT NULL,
                email TEXT NOT NULL,
                pass TEXT NOT NULL
            )
            
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("pass", user.pass)

        val db = this.writableDatabase
        db.insert("users", null, values)

        db.close()

    }
    fun getUser(login: String, pass: String): Boolean{
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE login = ? AND pass = ?", arrayOf(login, pass))
        val userExists = cursor.moveToFirst()
        cursor.close()
        return userExists
    }

}