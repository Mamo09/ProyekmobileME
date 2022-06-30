package com.example.tugasproyek.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasproyek.R
import com.example.tugasproyek.`interface`.Communicator
import com.example.tugasproyek.adapters.TurnamenRecyclerAdapter
import com.example.tugasproyek.dataclass.TurnamenDataClass
import kotlinx.android.synthetic.main.fragment_turnamen.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TurnamenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TurnamenFragment : Fragment(),TurnamenRecyclerAdapter.OnItemClickListener {

    private val turnamenList = turnamenArrayList()
    private val turnamenAdapter = TurnamenRecyclerAdapter(turnamenList,this)


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
        return inflater.inflate(R.layout.fragment_turnamen, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TurnamenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TurnamenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        turnamenRecyclerView.adapter = turnamenAdapter
        turnamenRecyclerView.layoutManager = LinearLayoutManager(activity)
        turnamenRecyclerView.setHasFixedSize(true)



    }

    override fun onItemClick(position: Int) {

        communicator = activity as Communicator

        communicator.passDataTurnamen(turnamenList[position].images,turnamenList[position].titles,turnamenList[position].dates,turnamenList[position].categories)

    }

    private fun turnamenArrayList(): List<TurnamenDataClass>{
        var titles = arrayOf(
            "Tuesday Aggressor eSports Community Mobile Legends Ruby Tournament 1",
            "Tuesday Aggressor eSports Community Mobile Legends Ruby Tournament 2",
            "Tuesday Aggressor eSports Community Mobile Legends Ruby Tournament 3",
            "Tuesday Aggressor eSports Community Mobile Legends Ruby Tournament 4")

        var dates = arrayOf("17 Agustus 1945","17 Agustus 1945","17 Agustus 1945","17 Agustus 1945")

        var categories = arrayOf("Mobile Legends","Mobile Legends","Mobile Legends","Mobile Legends")

        var images = intArrayOf(
            R.drawable.rubytournament,
            R.drawable.rubytournament,
            R.drawable.rubytournament,
            R.drawable.rubytournament
        )

        val list = ArrayList<TurnamenDataClass>()
        for (i in 0 until titles.size){
            val item = TurnamenDataClass(images[i],titles[i], dates[i],categories[i])
            list += item
        }
        return list
    }
}