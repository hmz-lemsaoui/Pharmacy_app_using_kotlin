package com.example.e_commerceapp.Healper

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.e_commerceapp.Activity.MainActivity
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Domain.User


class DbHelper(var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {




    override fun onCreate(db: SQLiteDatabase) {
        var data = ArrayList<RecomendedDomain>()
        data.apply {

        add(RecomendedDomain("Statines","sachet1", "Sachet","Analgésiques (antidouleur) tels que l'acétaminophène et l'ibuprofène",13.0,5,20,100,1))
        add(RecomendedDomain("Diurétiques","sachet2","Sachet","Antibiotiques pour les infections",14.0,4,20,100,1))
        add(RecomendedDomain("Statines","sachet3", "Sachet","Insuline pour le diabète",15.0,3,20,100,1))
        add(RecomendedDomain("Insuline","sachet4", "Sachet","Statines pour le cholestérol élevé",16.0,2,20,100,1))
        add(RecomendedDomain("Bêtabloquants","sachet5", "Sachet","Bêtabloquants pour l'hypertension artérielle et les problèmes cardiaques",17.0,1,20,100,1))
        add(RecomendedDomain("Diurétiques","sachet6", "Sachet","Diurétiques pour l'hypertension artérielle et l'œdème (accumulation de liquide)",18.0,5,20,100,1))

        add(RecomendedDomain("Insuline","vitamin1", "Vitamin","Statines pour le cholestérol élevé",16.0,2,20,100,1))
        add(RecomendedDomain("Bêtabloquants","vitamin2", "Vitamin","Bêtabloquants pour l'hypertension artérielle et les problèmes cardiaques",17.0,1,20,100,1))
        add(RecomendedDomain("Diurétiques","vitamin3", "Vitamin","Diurétiques pour l'hypertension artérielle et l'œdème (accumulation de liquide)",18.0,5,20,100,1))
        add(RecomendedDomain("Analgésiques","img12", "Vitamin","Analgésiques (antidouleur) tels que l'acétaminophène et l'ibuprofène",13.0,5,20,100,1))
        add(RecomendedDomain("Antibiotiques","img13","Vitamin","Antibiotiques pour les infections",14.0,4,20,100,1))
        add(RecomendedDomain("Bêtabloqua","img14", "Vitamin","Bêtabloquants pour l'hypertension artérielle et les problèmes cardiaques",17.0,1,20,100,1))
        add(RecomendedDomain("Statines","img15", "Vitamin","Bêtabloquants pour l'hypertension artérielle et les problèmes cardiaques",17.0,1,20,100,1))

        add(RecomendedDomain("Insuline","capsule2", "Gélule","Insuline pour le diabète",15.0,3,20,100,1))
        add(RecomendedDomain("Statines","capsule1", "Gélule","Statines pour le cholestérol élevé",16.0,2,20,100,1))
        add(RecomendedDomain("Bêtabloquants","img1", "Gélule","Bêtabloquants pour l'hypertension artérielle et les problèmes cardiaques",17.0,1,20,100,1))
        add(RecomendedDomain("Diurétiques","img2", "Gélule","Diurétiques pour l'hypertension artérielle et l'œdème (accumulation de liquide)",18.0,5,20,100,1))
        add(RecomendedDomain("Antihistaminiques","img3", "Gélule","Antihistaminiques pour les allergies",19.0,4,20,100,1))
        add(RecomendedDomain("Diurétiques","img4","Gélule","Antidépresseurs pour la dépression et l'anxiété",20.0,3,20,100,1))

        add(RecomendedDomain("Antipyrétiques","img5", "Comprimé","Antipyrétiques pour la fièvre",21.0,2,20,100,1))
        add(RecomendedDomain("Statines","img6", "Comprimé","Analgésiques (antidouleur) tels que l'acétaminophène et l'ibuprofène",22.0,1,20,100,1))
        add(RecomendedDomain("Analgésiques","img7", "Comprimé","Analgésiques (antidouleur) tels que l'acétaminophène et l'ibuprofène",23.0,5,20,100,1))
        add(RecomendedDomain("Insuline","img8", "Comprimé","Insuline pour le diabète",15.0,3,20,100,1))
        add(RecomendedDomain("Statines","img9", "Comprimé","Analgésiques (antidouleur) tels que l'acétaminophène et l'ibuprofène",13.0,5,20,100,1))
        add(RecomendedDomain("Diurétiques","img10","Comprimé","Antibiotiques pour les infections",14.0,4,20,100,1))
        add(RecomendedDomain("Statines 2","img11", "Comprimé","Insuline pour le diabète",15.0,3,20,100,1))


            add(RecomendedDomain("Analgésiques","sirop1", "Sirop","Analgésiques (antidouleur) tels que l'acétaminophène et l'ibuprofène",13.0,5,20,100,1))
            add(RecomendedDomain("Antibiotiques","sirop2","Sirop","Antibiotiques pour les infections",14.0,4,20,100,1))
            add(RecomendedDomain("Insuline","sirop3", "Sirop","Insuline pour le diabète",15.0,3,20,100,1))
            add(RecomendedDomain("Statines","sirop4", "Sirop","Statines pour le cholestérol élevé",16.0,2,20,100,1))
            add(RecomendedDomain("Bêtabloquants","sirop5", "Sirop","Bêtabloquants pour l'hypertension artérielle et les problèmes cardiaques",17.0,1,20,100,1))
            add(RecomendedDomain("Diurétiques","sirop6", "Sirop","Diurétiques pour l'hypertension artérielle et l'œdème (accumulation de liquide)",18.0,5,20,100,1))
            add(RecomendedDomain("Antihistaminiques","sirop7", "Sirop","Antihistaminiques pour les allergies",19.0,4,20,100,1))
            add(RecomendedDomain("Diurétiques","sirop8","Sirop","Antidépresseurs pour la dépression et l'anxiété",20.0,3,20,100,1))
            add(RecomendedDomain("Antipyrétiques","sirop9", "Sirop","Antipyrétiques pour la fièvre",21.0,2,20,100,1))
            add(RecomendedDomain("Statines","sirop10", "Sirop","Analgésiques (antidouleur) tels que l'acétaminophène et l'ibuprofène",22.0,1,20,100,1))
            add(RecomendedDomain("Analgésiques","sirop11", "Sirop","Analgésiques (antidouleur) tels que l'acétaminophène et l'ibuprofène",23.0,5,20,100,1))

        }
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
        db?.execSQL(
            """ CREATE TABLE $TABLE_NAME_MED(
                $COL_ID_MED INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_TITLE VARCHAR(256),
                $COL_DISCRIPTION VARCHAR(256),
                $COL_PRICE REAL,
                $COL_CATEGORY VARCHAR(256),
                $COL_PICTURE VARCHAR(256),
                $COL_STAR INTEGER,
                $COL_TIME INTEGER,
                $COL_NUMBERINCARD INTEGER,
                $COL_ISFAVORITE INTEGER)
        """.trimIndent()
        )

        val values = ContentValues()
        data.forEach {
            values.apply {
                put(COL_TITLE, it.title)
                put(COL_PICTURE, it.pic)
                put(COL_DISCRIPTION, it.description)
                put(COL_PRICE, it.price)
                put(COL_CATEGORY, it.category)
                put(COL_STAR, it.star)
                put(COL_TIME, it.time)
                put(COL_NUMBERINCARD, it.numberInCart)
                put(COL_ISFAVORITE, it.isFavorite)
            }
            db?.insert(TABLE_NAME_MED, null, values)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_MED")
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
        val cur: Cursor = db.rawQuery(
            "SELECT $COL_ID, $COL_NAME, $COL_EMAIL, $COL_PASSWORD FROM $TABLE_NAME WHERE $COL_EMAIL=? AND $COL_PASSWORD=?",
            arrayOf(value.email, value.password)
        )
        if (cur != null) {
            if (cur.moveToFirst()) {
                val id = cur.getInt(0)
                val name = cur.getString(1)
                val email = cur.getString(2)
                val password = cur.getString(3)
                user = User(id = id, name = name, email = email, password = password)
            }
        }
        closeElements(db, cur)
        return user
    }

    fun logout(context: Context) {
        userName = ""
        val intent = Intent(context, MainActivity::class.java)
    }

    fun checkIfEmailExist(email: String): Boolean {
        val db = this.readableDatabase
        val cur: Cursor =
            db.rawQuery("SELECT $COL_ID FROM $TABLE_NAME WHERE $COL_EMAIL=?", arrayOf(email))
        if (cur != null) {
            if (cur.moveToFirst()) {
                closeElements(db, cur)
                return true
            }
        }
        closeElements(db, cur)
        return false
    }

    private fun closeElements(db: SQLiteDatabase, cur: Cursor) {
        cur.close()
        db.close()
    }

    fun updateUser(
        c: Context,
        id: Int,
        mobile: String,
        adress: String,
        postalCode: String
    ) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_MOBILE, mobile)
            put(COL_ADRESS, adress)
            put(COL_CODE, postalCode)
        }
        val selection = "$COL_ID = ?"
        val selectionArgs = arrayOf(id.toString())
        val count = db.update(TABLE_NAME, values, selection, selectionArgs)
        db.close()
    }

    fun getAllMedsByCategory(catgory: String): ArrayList<RecomendedDomain> {
        val medList = ArrayList<RecomendedDomain>()
        val db = this.readableDatabase
        val cur: Cursor = db.rawQuery(
            """
                SELECT $COL_ID_MED,
                $COL_TITLE,
                $COL_DISCRIPTION,
                $COL_PRICE,
                $COL_CATEGORY,
                $COL_PICTURE,
                $COL_STAR,
                $COL_TIME,
                $COL_NUMBERINCARD,
                $COL_ISFAVORITE
                FROM $TABLE_NAME_MED WHERE $COL_CATEGORY=?
            """.trimIndent(),arrayOf(catgory)
        )
        while (cur.moveToNext()) {
            var fav = false
            if (cur.getInt(9) == 1)
                fav = true
            medList.add(
                RecomendedDomain(
                    cur.getString(1),cur.getString(5),
                    cur.getString(4),cur.getString(2),
                    cur.getDouble(3), cur.getInt(6),
                    cur.getInt(7), 0,
                    cur.getInt(8),fav
                )
            )
        }
        closeElements(db, cur)
        return medList
    }


    companion object {
        // Database Version
        private val DATABASE_VERSION = 11

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

        // table medicaments
        private val TABLE_NAME_MED = "MEDICAMENTS"
        private val COL_ID_MED = "med_id"
        private val COL_TITLE = "med_title"
        private val COL_DISCRIPTION = "med_discription"
        private val COL_PRICE = "med_price"
        private val COL_CATEGORY = "med_category"
        private val COL_PICTURE = "med_picture"
        private val COL_STAR = "med_star"
        private val COL_TIME = "med_time"
        private val COL_NUMBERINCARD = "med_numberInCard"
        private val COL_ISFAVORITE = "med_isFavorite"


    }

}