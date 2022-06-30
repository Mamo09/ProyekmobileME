package com.example.tugasproyek

import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.Dialog
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.tugasproyek.`interface`.Communicator
import com.example.tugasproyek.adapters.DetailTurnamenViewPagerAdapter
import com.example.tugasproyek.databinding.ActivityHomeBinding
import com.example.tugasproyek.databinding.ActivityLogin2Binding
import com.example.tugasproyek.fragments.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.dialog_gearbox.*
import kotlinx.android.synthetic.main.fragment_beranda.*
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlin.system.exitProcess



@Suppress("UNUSED_VALUE")
class Home : AppCompatActivity(),Communicator {
    private val berandaFragment = BerandaFragment()
    private val scheduleFragment = ScheduleFragment()
    private  val turnamenFragment = TurnamenFragment()
    private val profilFragment = ProfilFragment()

    private lateinit var Auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        val pemainFinished = intent.getStringExtra("pemainFinished")

        val joinFinished = intent.getStringExtra("joinFinished")
        val dialogStatus = intent.getBooleanExtra("dialogStatus", true)


        if (pemainFinished == "true"){
            replaceFragment(profilFragment)
        }else if (joinFinished == "true"){
            replaceFragment(turnamenFragment)
        }else{
            replaceFragment(berandaFragment)
        }


        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.beranda -> replaceFragment(berandaFragment)
                R.id.schedule -> replaceFragment(scheduleFragment)
                R.id.turnamen -> replaceFragment(turnamenFragment)
                R.id.profil -> replaceFragment(profilFragment)

            }
            true
        }









        //if (dialogStatus){
          //  showDialog()
        //}



    }


   // private fun showDialog() {
     //   val view = LayoutInflater.from(this).inflate(R.layout.dialog_gearbox,null)
     //   val mBuilder = AlertDialog.Builder(this)
      //  mBuilder.setView(view)

     //   mBuilder.setCancelable(false)
      //  val mAlertDialog = mBuilder.show()

     //   mAlertDialog.btnClaim.setOnClickListener {
      //      mAlertDialog.dismiss()
       // }


   // }


    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment).commit()
        }
    }


    override fun passDataPosting(images: Int, titles: String, dates: String,authors:String, bodies: String) {
        val bundle = Bundle()
        bundle.putString("heading",titles)
        bundle.putString("body",bodies)
        bundle.putString("date",dates)
        bundle.putString("author",authors)
        bundle.putInt("image",images)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentPosting = PostingFragment()
        fragmentPosting.arguments = bundle
        transaction.replace(R.id.fragment_container,fragmentPosting)
        transaction.commit()

    }

    override fun passDataTurnamen(images: Int, titles: String, dates: String, categories: String) {
        val bundle = Bundle()
        bundle.putString("heading",titles)
        bundle.putString("category",categories)
        bundle.putString("date",dates)
        bundle.putInt("image",images)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentTurnamen = DetailTurnamenFragment()
        fragmentTurnamen.arguments = bundle
        transaction.replace(R.id.fragment_container,fragmentTurnamen)
        transaction.commit()
    }
    override fun passDataSchedule(dates: String, team1: String, team2: String, categories: String) {
        val bundle = Bundle()
        bundle.putString("dates",dates)
        bundle.putString("team1",team1)
        bundle.putString("team2",team2)
        bundle.putString("category",categories)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentSchedule = ScheduleFragment()
        fragmentSchedule.arguments = bundle
        transaction.replace(R.id.fragment_container,fragmentSchedule)
        transaction.commit()
    }

    override fun overviewTurnamen(){
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager2)

        val adapter = DetailTurnamenViewPagerAdapter(supportFragmentManager,lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2){ tab, position->
            when(position){
                0->{
                    tab.text = "Overview"
                }
                1->{
                    tab.text = "Bracket"
                }
                2->{
                    tab.text = "Result"
                }
            }
        }.attach()









//        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//                super.onPageScrolled(position,positionOffset,positionOffsetPixels)
//                if (position>0 && positionOffset==0.0f && positionOffsetPixels==0){
//                    viewPager2.layoutParams.height =
//                        viewPager2.getChildAt(0).height
//                }
//            }
//        })




    }


    private var backPressedTime:Long = 0
    lateinit var backToast: Toast

    override fun onBackPressed() {

        val backStackCount = supportFragmentManager.backStackEntryCount

        if (backStackCount == 0) {
            backToast =
                Toast.makeText(this, "Press back again to leave the app.", Toast.LENGTH_LONG)

            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                moveTaskToBack(true);
                exitProcess(-1)
            } else {
                backToast.show()
            }
            backPressedTime = System.currentTimeMillis()
        } else {
            super.onBackPressed()
        }
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
                sp?.play(soundID,.50f,.50f,1,0,.99f)
            }
        }
        soundID = sp?.load(this, R.raw.okaeri,1) ?: 0

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