package com.example.tugasproyek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

class CreateTeam : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_create_team)

        supportActionBar?.hide()


    }
}