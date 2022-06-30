package com.example.tugasproyek

import android.annotation.TargetApi
import android.app.AlarmManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.app.PendingIntent
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.example.tugasproyek.fragments.ScheduleFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        var alarmIntent = Intent(this, WidgetME ::class.java).let {
            it.action= WidgetME.ACTION_AUTO_UPDATE
            PendingIntent.getBroadcast(this, 101,it ,PendingIntent.FLAG_UPDATE_CURRENT)
        }
        var cal = Calendar.getInstance()
        cal.add(Calendar.MINUTE,1)

        var alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC , cal.timeInMillis, 60000L, alarmIntent)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
        },3000)

    }
    var sp : SoundPool? = null
    var soundID  : Int = 0
    override fun onStart() {
        super.onStart()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            createNewSoundPool()
        else
            createOldSoundPool()

        sp?.setOnLoadCompleteListener{soundPool, id, status ->

            if(soundID!=0) {
                sp?.play(soundID,.99f,.99f,1,0,.99f)
            }
        }
        soundID = sp?.load(this, R.raw.mikro,1) ?: 0

    }
    @TargetApi(Build.VERSION_CODES.Q)
    private fun createNewSoundPool() {
        sp = SoundPool.Builder()
            .setMaxStreams(15)
            .build()
    }
    @Suppress("DEPRECATION")
    private fun createOldSoundPool() {
        sp = SoundPool(15, AudioManager.STREAM_MUSIC,0)
    }

    override fun onStop() {
        super.onStop()
        sp?.release()
        sp = null
    }

}
