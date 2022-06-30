package com.example.tugasproyek.SQLite.myDB

import android.provider.BaseColumns

object userDB {
    class userTable: BaseColumns {
        companion object {
            val TABLE_USER = "tbl_user"
            val COLUMN_ID = "user_id"
            val COLUMN_NAME = "user_nama"
            val COLUMN_NIM = "user_nim"
            val COLUMN_SERVER_GAME = "user_server"
            val COLUMN_ID_IN_GAME = "user_idgame"
            val COLUMN_TEAM_NAME = "user_namatim"
        }
    }
}
