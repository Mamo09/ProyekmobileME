package com.example.tugasproyek.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tugasproyek.SQLite.myDB.userDB


class myDBHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME,null, DATABASE_VERSION){

    companion object {
        private val DATABASE_NAME ="mysqlitedb.db"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var CREATE_USER_TABLE = "CREATE TABLE ${userDB.userTable.TABLE_USER}"+
                "(${userDB.userTable.COLUMN_ID} INTEGER PRIMARY KEY, "+
                "${userDB.userTable.COLUMN_NAME} TEXT,"+
                "${userDB.userTable.COLUMN_NIM} TEXT," +
                "${userDB.userTable.COLUMN_SERVER_GAME} TEXT,"+
                "${userDB.userTable.COLUMN_ID_IN_GAME} TEXT,"+
                "${userDB.userTable.COLUMN_TEAM_NAME} TEXT)"
        db?.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${userDB.userTable.TABLE_USER}")
        onCreate(db)
    }

    fun addUser (user: User) : Long{

        var db = this.writableDatabase
        var contentValues: ContentValues = ContentValues().apply {
            put(userDB.userTable.COLUMN_NAME, user.nama)
            put(userDB.userTable.COLUMN_NIM, user.nim)
            put(userDB.userTable.COLUMN_SERVER_GAME, user.server)
            put(userDB.userTable.COLUMN_ID_IN_GAME, user.idgame)
            put(userDB.userTable.COLUMN_TEAM_NAME, user.namatim)

        }
        var success = db.insert(userDB.userTable.TABLE_USER,null,contentValues)
        db.close()
        return success
    }

    fun viewAllName() : List<String> {
        val nameList =ArrayList<String>()
        val SELECT_NAME = "SELECT ${userDB.userTable.COLUMN_NAME} FROM " +
                "${userDB.userTable.TABLE_USER}"
        var db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(SELECT_NAME,null)
        }catch ( e : SQLException){
            return ArrayList()
        }
        var userName = ""
        if( cursor.moveToFirst()){
            do{
                userName = cursor.getString(cursor.getColumnIndexOrThrow(userDB.userTable.COLUMN_NAME))
                nameList.add(userName)
            }while(cursor.moveToNext())
        }
        return  nameList
    }

    fun deleteUser(nama : String){
        var db = this.writableDatabase
        val selection = "${userDB.userTable.COLUMN_NAME} = ?"
        val selectionArgs = arrayOf(nama)
        db.delete(userDB.userTable.TABLE_USER,selection,selectionArgs)
    }


}

//    companion object {
//        private const val DATABASE_VERSION = 1
//        private const val DATABASE_NAME = "mysqlitedbex.db"
//    }
//
//    override fun onCreate(db: SQLiteDatabase?) {
//        var CREATE_USER_TABLE = ("CREATE TABLE ${userDB.userTable.TABLE_USER} " +
//                "(${userDB.userTable.COLUMN_ID} INTEGER PRIMARY KEY," +
//                "${userDB.userTable.COLUMN_NAME} TEXT," +
//                "${userDB.userTable.COLUMN_NIM} TEXT," +
//                "${userDB.userTable.COLUMN_SERVER_GAME} TEXT),"+
//                "${userDB.userTable.COLUMN_ID_IN_GAME} TEXT),"+
//                "${userDB.userTable.COLUMN_TEAM_NAME} TEXT)")
//        db!!.execSQL(CREATE_USER_TABLE)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
//        db?.execSQL(
//            "DROP TABLE IF EXISTS " + "${userDB.userTable.TABLE_USER}"
//        )
//        onCreate(db)
//    }
//
//    fun addUser(user: User): Long {
//        val db = this.writableDatabase
//        val contentValues = ContentValues().apply {
//            put(userDB.userTable.COLUMN_NAME, user.nama)
//            put(userDB.userTable.COLUMN_NIM, user.nim)
//            put(userDB.userTable.COLUMN_SERVER_GAME, user.server)
//            put(userDB.userTable.COLUMN_ID_IN_GAME, user.idgame)
//            put(userDB.userTable.COLUMN_TEAM_NAME, user.namatim)
//        }
//        val success = db.insert(
//            userDB.userTable.TABLE_USER,
//            null, contentValues
//        )
//        db.close()
//        return success
//    }
//
//    @SuppressLint("Range", "Recycle")
//    fun viewAllName(): List<String> {
//        val nameList = ArrayList<String>()
//        val SELECT_NAME = "SELECT ${userDB.userTable.COLUMN_NAME} " + " FROM ${userDB.userTable.TABLE_USER}"
//        val db = this.readableDatabase
//        var cursor : Cursor? = null
//        try {
//            cursor = db.rawQuery(SELECT_NAME, null)
//        } catch (e: SQLException) {
//            db.execSQL(SELECT_NAME)
//            return ArrayList()
//        }
//
//        var userName : String = ""
//        if (cursor.moveToFirst()){
//            do {
//                userName = cursor.getString(cursor.getColumnIndex(userDB.userTable.COLUMN_NAME))
//                nameList.add(userName)
//
//            } while (cursor.moveToNext())
//        }
//        return nameList
//    }
//
//    fun deleteData(nama: String){
//        val db = this.writableDatabase
//        val selection = "${userDB.userTable.COLUMN_NAME} = ?"
//        val selectionArgs = arrayOf(nama)
//        db.delete(userDB.userTable.TABLE_USER,selection,selectionArgs)
//    }


//}