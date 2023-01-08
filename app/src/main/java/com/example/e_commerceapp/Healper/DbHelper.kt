package com.example.e_commerceapp.Healper

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.e_commerceapp.Activity.MainActivity
import com.example.e_commerceapp.Domain.User


class DbHelper(var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        db?.execSQL(
            """ CREATE TABLE $TABLE_NAME(
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NAME VARCHAR(256),
                $COL_EMAIL VARCHAR(256),
                $COL_PASSWORD VARCHAR(256),
                $COL_MOBILE VARCHAR(256),
                $COL_ADRESS VARCHAR(256),
                $COL_CODE VARCHAR(256),
                $COL_IMAGE INTEGER)
        """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertUser(user: User): Boolean {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.apply {
            put(COL_NAME, user.name)
            put(COL_EMAIL, user.email)
            put(COL_PASSWORD, user.password)
        }
        var result = db.insert(TABLE_NAME, null, cv).toInt()
        db.close()
        return result != -1
    }


    fun verifyLogin(value: User): User? {
        var user: User? = null
        val db = this.readableDatabase
        val cur: Cursor = db.rawQuery("SELECT $COL_ID, $COL_NAME, $COL_EMAIL, $COL_PASSWORD FROM $TABLE_NAME WHERE $COL_EMAIL=? AND $COL_PASSWORD=?", arrayOf(value.email,value.password))
        if (cur != null){
            if (cur.moveToFirst()){
                val id = cur.getInt(0)
                val name = cur.getString(1)
                val email = cur.getString(2)
                val password = cur.getString(3)
                user = User(id = id,name=name,email=email,password=password)
            }
        }
        closeElements(db,cur)
        return user
    }

    fun logout(context: Context) {
        userName = ""
        val intent = Intent(context, MainActivity::class.java)
    }

    fun checkIfEmailExist(email: String):Boolean {
        val db = this.readableDatabase
        val cur: Cursor = db.rawQuery("SELECT $COL_ID FROM $TABLE_NAME WHERE $COL_EMAIL=?", arrayOf(email))
        if (cur != null) {
            if (cur.moveToFirst()) {
                closeElements(db,cur)
                return true
            }
        }
        closeElements(db,cur)
        return false
    }
    private fun closeElements(db: SQLiteDatabase,cur: Cursor){
        cur.close()
        db.close()
    }

    fun updateUser(
        c: Context,
        id: Int,
        mobile: String,
        adress: String,
        postalCode: String,
        img: Int
    ) {
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

    companion object {
        // Database Version
        private val DATABASE_VERSION = 1
        // Database Name
        private val DATABASE_NAME = "DbApp"
        // table users
        private val TABLE_NAME = "USERS"
        private val COL_ID = "user_id"
        private val COL_NAME = "user_name"
        private val COL_EMAIL = "user_email"
        private val COL_PASSWORD = "user_password"
        private val COL_MOBILE = "user_mobile"
        private val COL_ADRESS = "user_adress"
        private val COL_CODE = "user_codepostal"
        private val COL_IMAGE = "user_image"
        var userName = "ha"
    }

}