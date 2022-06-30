package com.example.tugasproyek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.WindowManager
import android.widget.Toast
import com.example.tugasproyek.databinding.ActivityLogin2Binding
import com.example.tugasproyek.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

//    lateinit var emailsave:String
//    lateinit var passwordsave:String
//    lateinit var namesave:String
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {

            val email = binding.email.text.toString().trim()
            val pass = binding.password1.text.toString().trim()
            val name = binding.nameUser.text.toString()

            if(name.isEmpty()){
                Toast.makeText(this, "Nama Harus Di Isi", Toast.LENGTH_SHORT).show()
            }

            if (email.isEmpty()){
                Toast.makeText(this, "Email Harus Di Isi ", Toast.LENGTH_SHORT).show()
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this, "Email Tidak Valid", Toast.LENGTH_SHORT).show()
            }

            if (pass.isEmpty() || pass.length < 6){
                Toast.makeText(this, "Password Harus Lebih dari 6 karakter", Toast.LENGTH_SHORT).show()
            }

            registeruser(email, pass)
        }

        binding.btnLogintoregister.setOnClickListener {
            Intent( this, Login :: class.java).also {
                startActivity(it)
            }

        }
    }

    private fun registeruser(email: String, pass: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Intent(this@Register, Home::class.java).also {intent ->
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}





//    override fun onBackPressed() {
//        super.onBackPressed()
//        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
//    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString("emailsave",email.text.toString())
//        outState.putString("passwordsave",password2.text.toString())
//        outState.putString("namesave",password1.text.toString())
//    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        emailsave = savedInstanceState.getString("emailsave","")
//        passwordsave = savedInstanceState.getString("passwordsave","")
//        namesave = savedInstanceState.getString("namesave","")
//
//        password1.setText(namesave)
//        email.setText(emailsave)
//        password2.setText(passwordsave)
//    }


