package com.example.tugasproyek

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.tugasproyek.databinding.ActivityLogin2Binding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login2.*
import kotlin.system.exitProcess

class Login : AppCompatActivity() {

    //lateinit var email:String
    //lateinit var password:String

//firebase
    private lateinit var binding: ActivityLogin2Binding
    private lateinit var firebaseAuth: FirebaseAuth


    var notificationManager: NotificationManager? = null
    //var email = ""

    companion object {
        const val EXTRA_STATUS = "EXTRA_STATUS" // const key to save/read value from bundle
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login2)
        supportActionBar?.hide()
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegistertologin.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.btnlogin.setOnClickListener {
            val email = binding.txtEmail.text.toString().trim()
            val pass = binding.txtPassword.text.toString().trim()

            if (email.isEmpty()){
                Toast.makeText(this, "Email Harus Di Isi ", Toast.LENGTH_SHORT).show()
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this, "Email Tidak Valid", Toast.LENGTH_SHORT).show()
            }

            if (pass.isEmpty() || pass.length < 6){
                Toast.makeText(this, "Password Harus Lebih dari 6 karakter", Toast.LENGTH_SHORT).show()
            }
            loginUser(email,pass)
            showNotification()
        }
    }

    private fun loginUser(email: String, pass: String) {
        firebaseAuth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener(this) {
                if (it.isSuccessful){
                     Intent(this, Home::class.java).also {intent ->
                         intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                         startActivity(intent)
                     }
                }else{
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }

    }
//    override fun onStart(){
//        super.onStart()
//        if (firebaseAuth.currentUser != null){
//            Intent(this, Home::class.java).also {
//                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(it)
//            }
//        }
//    }



//    override fun onStart() {
//        super.onStart()
//
//        if(firebaseAuth.currentUser != null){
//            val intent = Intent(this, Home::class.java)
//            startActivity(intent)
//        }
//    }


//        btnLogin.setOnClickListener {
//            if(TextUtils.isEmpty(txtEmail.text.toString())) {
//                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show()
//            } else if (TextUtils.isEmpty(txtPassword.text.toString())){
//                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show()
//            }else{
//                val intent = Intent(this,Home::class.java)
//
//
//                if (txtEmail.text.toString() == "tommy@gmail.com" && txtPassword.text.toString() == "tommy"){
//                    nama = "Tommy Christian Siregar"
//                }else if(txtEmail.text.toString() == "futma@gmail.com" && txtPassword.text.toString() == "futma"){
//                    nama = "Futma Nurhidayat"
//                }else if(txtEmail.text.toString() == "gideon@gmail.com" && txtPassword.text.toString() == "gideon"){
//                    nama = "Gideon Perdamaian Hulu"
//                }
//
//                startActivity(intent)
//                showNotification()
//
//            }
//
//        }
//
//        btn_registertologin.setOnClickListener {
//            startActivity(Intent(this,Register::class.java))
//            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_from_left)
//        }

//    }

    private fun showNotification() {
        var channel_id = "notif"

        val intent = Intent(this, Home::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run{
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        var myNotification = NotificationCompat.Builder(this, channel_id)
            .setContentTitle("Login Berhasil")
            .setSmallIcon(R.drawable.meputih)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("selamat datang"),
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notificationManager?.notify(1, myNotification.build())




    }

//    private var backPressedTime:Long = 0
//    lateinit var backToast: Toast
//
//    override fun onBackPressed() {
//        backToast = Toast.makeText(this, "Press back again to leave the app.", Toast.LENGTH_LONG)
//
//        if (backPressedTime + 2000 > System.currentTimeMillis()) {
//            moveTaskToBack(true);
//            exitProcess(-1)
//        } else {
//            backToast.show()
//        }
//        backPressedTime = System.currentTimeMillis()
//
//    }

    //override fun onSaveInstanceState(outState: Bundle) {
   //     super.onSaveInstanceState(outState)
    //    outState.putString("email",email)
    //    outState.putString("password",password)
   // }

   // override fun onRestoreInstanceState(savedInstanceState: Bundle) {
   //     super.onRestoreInstanceState(savedInstanceState)
    //    email = savedInstanceState.getString("email","")
    //    password = savedInstanceState.getString("password","")
   // }



}
