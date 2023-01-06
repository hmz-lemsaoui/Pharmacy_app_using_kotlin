package com.example.e_commerceapp.Healper




import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.Editable
import android.widget.Toast
import com.example.e_commerceapp.Activity.MainActivity
import com.example.e_commerceapp.models.*


// Database Version
private val DATABASE_VERSION = 1

// Database Name
private val DATABASE_NAME = "DbApp"
private val TABLE_NAME="USERS"
private val COL_ID="user_id"
private val COL_NAME="user_name"
private val COL_EMAIL="user_email"
private val COL_PASSWORD="user_password"
private val COL_MOBILE="user_mobile"
private val COL_ADRESS="user_adress"
private val COL_CODE="user_codepostal"
private val COL_IMAGE="user_image"
var userName = "ha"

class DbHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        db?.execSQL("CREATE TABLE $TABLE_NAME(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_EMAIL + " VARCHAR(256)," +
                COL_PASSWORD + " VARCHAR(256)," +
                COL_MOBILE + " VARCHAR(256)," +
                COL_ADRESS + " VARCHAR(256)," +
                COL_CODE + " VARCHAR(256)," +
                COL_IMAGE + " INTEGER )")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
//        onCreate(db)
    }
    fun insertUser(user: User) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, user.name)
        cv.put(COL_EMAIL, user.email)
        cv.put(COL_PASSWORD, user.password)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Register Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "successfully registered", Toast.LENGTH_SHORT).show()
    }


    @SuppressLint("Range")
    fun getUsers(): MutableList<User> {
        var list: MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var user = User(
                    result.getString(result.getColumnIndex(COL_ID)).toInt(),
                    result.getString(result.getColumnIndex(COL_NAME)),
                    result.getString(result.getColumnIndex(COL_EMAIL)),
                    result.getString(result.getColumnIndex(COL_PASSWORD)),
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
    fun logout(context: Context) {
        userName = ""
        val intent = Intent(context, MainActivity::class.java)

    }


    fun updateUser(c:Context,id: Int, mobile: String,adress: String, postalCode: String,img: Int) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_MOBILE, mobile)
            put(COL_ADRESS, adress)
            put(COL_CODE, postalCode)
            put(COL_IMAGE, img)
        }
        val selection = "$COL_ID = ?"
        val selectionArgs = arrayOf(id.toString())
        val count = db.update(TABLE_NAME, values, selection, selectionArgs)
        db.close()
    }

}