package com.example.tugasproyek.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.tugasproyek.R
import kotlinx.android.synthetic.main.fragment_posting.*
import kotlinx.android.synthetic.main.fragment_posting.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostingFragment : Fragment() {

    var imageDisplay: Int? = R.drawable.rubytournament
    var titleDisplay: String? = ""
    var dateDisplay: String? = ""
    var authorDisplay: String? = ""
    var bodyDisplay: String? = ""

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
        return inflater.inflate(R.layout.fragment_posting, container, false)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageDisplay = arguments?.getInt("image")
        titleDisplay = arguments?.getString("heading")
        dateDisplay = arguments?.getString("date")
        authorDisplay = arguments?.getString("author")
        bodyDisplay = arguments?.getString("body")
        postingHeading.text = titleDisplay
        imageDisplay?.let { postingImage.setImageResource(it) }
        postingDate.text = dateDisplay
        postingAuthor.text = authorDisplay
        postingBody.text = bodyDisplay
    }
}