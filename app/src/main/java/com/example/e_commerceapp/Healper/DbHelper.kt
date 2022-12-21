package com.example.e_commerceapp.Healper




import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.e_commerceapp.models.*


// Database Version
private val DATABASE_VERSION = 1

// Database Name
private val DATABASE_NAME = "DbApp"

var userName = "ha"

class DbHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        db?.execSQL("CREATE TABLE USERS (" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_name VARCHAR(256) ,"+
                "user_email VARCHAR(256)," +
                "user_password VARCHAR(256))")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
    fun insertUser(user: User) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put("user_name", user.name)
        cv.put("user_email", user.email)
        cv.put("user_password", user.password)
        var result = db.insert("USERS", null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Register Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "successfully registered", Toast.LENGTH_SHORT).show()
    }


    @SuppressLint("Range")
    fun getUsers(): MutableList<User> {
        var list: MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from USERS"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var user = User(
                    result.getString(result.getColumnIndex("user_id")).toInt(),
                    result.getString(result.getColumnIndex("user_name")),
                    result.getString(result.getColumnIndex("user_email")),
                    result.getString(result.getColumnIndex("user_password")),
                )
                list.add(user)
            } while (result.moveToNext())
        }

        result.close()
        db.close()

        return list
    }
    fun verifyLogin(value: User): User? {
        val users = getUsers()
        for (user in users) {
            if (user.email.equals(value.email) && user.password.equals(value.password)) {
                userName = user.name.toString()
                return user
            }
        }
        return null
    }

}