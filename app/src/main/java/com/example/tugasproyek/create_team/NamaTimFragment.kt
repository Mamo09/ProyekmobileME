package com.example.tugasproyek.create_team

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.tugasproyek.R
import kotlinx.android.synthetic.main.fragment_nama_tim.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NamaTimFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NamaTimFragment : Fragment() {
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
        val view =  inflater.inflate(R.layout.fragment_nama_tim, container, false)
        val viewPager =activity?.findViewById<ViewPager2>(R.id.createTeamViewPager)
        view.next.setOnClickListener {
            viewPager?.currentItem = 1
        }

        val pilihGameItem = resources.getStringArray(R.array.pilihGame)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.pilih_game_dropdown_item,pilihGameItem)
        view.autoCompleteTextView.setAdapter(arrayAdapter)
        view.autoCompleteTextView.setBackgroundColor(Color.parseColor("#1B2835"))
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NamaTimFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NamaTimFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}