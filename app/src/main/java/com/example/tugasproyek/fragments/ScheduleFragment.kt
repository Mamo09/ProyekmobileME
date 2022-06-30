package com.example.tugasproyek.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasproyek.R
import com.example.tugasproyek.`interface`.Communicator
import com.example.tugasproyek.adapters.ScheduleRecyclerAdapter
import com.example.tugasproyek.dataclass.scheduleDataClass
import kotlinx.android.synthetic.main.fragment_schedule.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleFragment : Fragment() {
    private val scheduleList = scheduleArrayList()
    private val scheduleAdapter= ScheduleRecyclerAdapter(scheduleList,this)

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
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScheduleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scheduleRecyclerView.adapter = scheduleAdapter
        scheduleRecyclerView.layoutManager = LinearLayoutManager(activity)
        scheduleRecyclerView.setHasFixedSize(true)

    }

    fun onItemClick(position: Int) {

        communicator = activity as Communicator

        communicator.passDataSchedule(scheduleList[position].dates,scheduleList[position].team1,scheduleList[position].team2,scheduleList[position].categories)

    }

    private fun scheduleArrayList(): List<scheduleDataClass>{
        var dates = arrayOf(
            "9 AM",
            "11 AM",
            "13 AM",
            "19 AM")

        var team1 = arrayOf("ME MAX","ME MIN","ME WIN","ME LOST")

        var team2 = arrayOf("BOOM","EVAS","RRZ","OMIC")

        var categories = arrayOf("BRONZE MATCH MIYA",
            "GOLD MATCH MIYA",
            "RRZ TOUR CHAMPIONS",
            "OMIC TOUR")


        val list = ArrayList<scheduleDataClass>()
        for (i in 0 until dates.size){
            val item = scheduleDataClass(dates[i],team1[i], team2[i], categories[i])
            list += item
        }
        return list
    }
}