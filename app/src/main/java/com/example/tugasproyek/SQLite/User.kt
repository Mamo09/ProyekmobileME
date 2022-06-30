package com.example.tugasproyek.SQLite

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User : Parcelable{
    var id : Int = 0
    var nama : String = ""
    var nim : String = ""
    var server : String = ""
    var idgame : String = ""
    var namatim : String = ""

}