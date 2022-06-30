package com.example.tugasproyek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tugasproyek.SQLite.myDBHelper
import com.example.tugasproyek.SQLite.User
import com.example.tugasproyek.databinding.ActivityNamaPemainBinding
import kotlinx.android.synthetic.main.activity_nama_pemain.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NamaPemain : AppCompatActivity() {
    var mySQLitedb : myDBHelper? = null
    //private lateinit var binding: ActivityNamaPemainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nama_pemain)
        supportActionBar?.hide()

        mySQLitedb = myDBHelper(this)

        btnSimpan.setOnClickListener {
            val userTmp = User()
            userTmp.nama = inputNamaPemain1.text.toString()
            userTmp.nim = inputNIKNIMPemain1.text.toString()
            userTmp.server = inputServerPemain1.text.toString()
            userTmp.idgame = inputIDPemain1.text.toString()
            userTmp.namatim = inputNamateam.text.toString()
            var result = mySQLitedb?.addUser(userTmp)
            if(result!=-1L){
                Toast.makeText(this, "Berhasil",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Gagal",Toast.LENGTH_SHORT).show()
            }
            updateAdapter()
            inputNamaPemain1.text.clear()
            inputNIKNIMPemain1.text.clear()
            inputServerPemain1.text.clear()
            inputIDPemain1.text.clear()
            inputNamateam.text.clear()

        }
        btnHapus.setOnClickListener {
            var nama = spinner1.selectedItem.toString()
            if(nama != null || nama != ""){
                doAsync {
                    mySQLitedb?.deleteUser(nama)
                    updateAdapter()
                }
            }
        }
    }

    private fun updateAdapter() {
        doAsync {
            var nameList = mySQLitedb?.viewAllName()?.toTypedArray()
            uiThread {
                if(spinner1 != null && nameList?.size != 0) {
                    var arrayAdapter = ArrayAdapter(applicationContext,
                        android.R.layout.simple_spinner_dropdown_item, nameList!!)
                    spinner1.adapter = arrayAdapter
                }
            }
        }
    }


}