package com.example.tugasproyek.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tugasproyek.*
import com.example.tugasproyek.databinding.FragmentProfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profil.*
import java.io.ByteArrayOutputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class ProfilFragment : Fragment() {


    private lateinit var firebaseAuth: FirebaseAuth
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var imageUri : Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfilFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        const val REQUEST_CAMERA = 100
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        val user = firebaseAuth.currentUser

        if (user!=null){
            if (user.photoUrl != null){
                Picasso.get().load(user.photoUrl).into(fotoProfil)
            }else{
                Picasso.get().load("https://picsum.photos/id/237/200/300").into(fotoProfil)
            }

            etName.setText(user.displayName)
            etEmail.setText(user.email)

            if (user.isEmailVerified){
                icVeri.visibility = View.VISIBLE

            }else{
                icUnveri.visibility = View.VISIBLE
            }

            if (user.phoneNumber.isNullOrEmpty()){
                etPhone.setText("Masukkan Nomor Telepon")
            }else{
                etPhone.setText(user.phoneNumber )
            }

        }

        fotoProfil.setOnClickListener{
            intentCamera()
        }

        btnDetail.setOnClickListener {
           startActivity(Intent(activity, NamaPemain::class.java))
        }

        btnLogout.setOnClickListener {
            btnLogout()
        }
        btnSave.setOnClickListener{
            val image = when{
                ::imageUri.isInitialized -> imageUri
                user?.photoUrl == null -> Uri.parse("https://picsum.photos/id/237/200/300")
                else -> user.photoUrl
            }
            val name = etName.text.toString().trim()

            if(name.isEmpty()){
                etName.error = "Nama Harus Diisi"
                etName.requestFocus()
                return@setOnClickListener
            }
            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(image)
                .build().also {
                    user?.updateProfile(it)?.addOnCompleteListener {
                        if (it.isSuccessful)(
                                Toast.makeText(activity, "Profile Updated",Toast.LENGTH_SHORT).show()

                        )else{
                            Toast.makeText(activity, "${it.exception?.message}",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }

        
//        firebaseAuth = FirebaseAuth.getInstance()
//        btnLogout.setOnClickListener{
//            firebaseAuth.signOut()
//            Intent(this, Login::class.java).also {intent ->
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//            }
//        }

    }

    private fun intentCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {intent ->
            activity?.packageManager?.let {
                intent.resolveActivity(it).also {
                    startActivityForResult(intent, REQUEST_CAMERA)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK ) {
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImage(imgBitmap)
        }
    }

    private fun uploadImage(imgBitmap: Bitmap) {
        val baos  = ByteArrayOutputStream()
        val ref = FirebaseStorage.getInstance().reference.child("img/${FirebaseAuth.getInstance().currentUser?.email}")

        imgBitmap.compress(Bitmap.CompressFormat.JPEG,100, baos)
        val image = baos.toByteArray()
        ref.putBytes(image)
            .addOnCompleteListener{
                if (it.isSuccessful){
                     ref.downloadUrl.addOnCompleteListener{Task ->
                         Task.result?.let {Uri ->
                             imageUri = Uri
                             fotoProfil.setImageBitmap(imgBitmap)

                         }
                     }
                }
            }
    }

    private fun btnLogout() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val intent = Intent(context,Login::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
