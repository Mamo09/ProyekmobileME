    package com.example.tugasproyek.create_team

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.tugasproyek.Home
import com.example.tugasproyek.R
import com.example.tugasproyek.`interface`.Communicator
import com.example.tugasproyek.create_team.pemain.*
import kotlinx.android.synthetic.main.fragment_nama_pemain.view.*
import kotlinx.android.synthetic.main.fragment_nama_tim.view.*
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

    // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NamaPemainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NamaPemainFragment : Fragment() {

    private lateinit var communicator: Communicator
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_nama_pemain, container, false)
        val viewPager =activity?.findViewById<ViewPager2>(R.id.createTeamViewPager)
        view.finish.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("pemainFinished", "true")
            bundle.putBoolean("dialogStatus", false)
            val intent = Intent(activity, Home::class.java)
            intent.putExtras(bundle)
            startActivity(intent)

        }
        view.previous.setOnClickListener {
            viewPager?.currentItem = 0
        }

        val fragmentList = arrayListOf<Fragment>(
            Pemain1Fragment(),
            Pemain2Fragment(),
            Pemain3Fragment(),
            Pemain4Fragment(),
            Pemain5Fragment(),
        )
        val adapter = DaftarPemainViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)
        view.daftarPemainViewPager.adapter = adapter

        //view.daftarPemainViewPager.isUserInputEnabled = false
        return view
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NamaPemainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NamaPemainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}