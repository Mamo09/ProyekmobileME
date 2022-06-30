package com.example.tugasproyek.join_turnamen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.tugasproyek.R
import com.example.tugasproyek.create_team.pemain.*
import kotlinx.android.synthetic.main.fragment_bayar.view.*
import kotlinx.android.synthetic.main.fragment_nama_pemain.view.*
import kotlinx.android.synthetic.main.fragment_bayar.view.previous as previous1

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BayarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BayarFragment : Fragment() {
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
        val view =  inflater.inflate(R.layout.fragment_bayar, container, false)
        val fragmentList = arrayListOf<Fragment>(
            KonfirmasiPembayaran1Fragment(),
            KonfirmasiPembayaran2Fragment(),
        )
        val adapter = KonfirmasiPembayaranViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)
        view.konfirmasiPembayaranViewPager.adapter = adapter

        val viewPager = activity?.findViewById<ViewPager2>(R.id.joinTurnamenViewPager)
        view.previous.setOnClickListener {
            viewPager?.currentItem = 0
        }
        view.next.setOnClickListener {
            viewPager?.currentItem = 2
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PemainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BayarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}