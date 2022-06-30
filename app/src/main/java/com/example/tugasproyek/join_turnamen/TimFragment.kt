package com.example.tugasproyek.join_turnamen

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.tugasproyek.R
import com.example.tugasproyek.databinding.FragmentTimBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TimFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimFragment : Fragment() {
    private lateinit var _binding: FragmentTimBinding
    private val binding get() = _binding
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
        _binding =  FragmentTimBinding.inflate(inflater, container, false)
        val view = binding.root
        val pilihTimItem = resources.getStringArray(R.array.pilihTim)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.pilih_tim_dropdown_item,pilihTimItem)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.autoCompleteTextView.setBackgroundColor(Color.parseColor("#1B2835"))

        val viewPager = activity?.findViewById<ViewPager2>(R.id.joinTurnamenViewPager)
        binding.next.setOnClickListener {
            viewPager?.currentItem = 1

            val sharedPreferences = requireActivity().getSharedPreferences("NotifSharedPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply {
                putString("team",binding.autoCompleteTextView.text.toString())
            }.apply()

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
         * @return A new instance of fragment TimFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}