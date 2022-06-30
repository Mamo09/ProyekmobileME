package com.example.tugasproyek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

class JoinTurnamen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_join_turnamen)

        supportActionBar?.hide()

    }


}