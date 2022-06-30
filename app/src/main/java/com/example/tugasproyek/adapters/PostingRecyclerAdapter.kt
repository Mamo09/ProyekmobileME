package com.example.tugasproyek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasproyek.R
import com.example.tugasproyek.dataclass.PostingDataClass

class PostingRecyclerAdapter(private var postingList:List<PostingDataClass>,private var listener: OnItemClickListener): RecyclerView.Adapter<PostingRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.posting_card_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = postingList[position]



//       holder.itemImage.setImageResource(currentItem.images)
//       holder.itemTitle.text = currentItem.titles
//       holder.itemDate.text = currentItem.dates
//       holder.itemAuthor.text = currentItem.authors
    }

    override fun getItemCount(): Int {
        return postingList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
//        var itemImage: ImageView= itemView.findViewById(R.id.item_gambar)
//        var itemTitle: TextView= itemView.findViewById(R.id.item_judul)
//        var itemDate: TextView= itemView.findViewById(R.id.item_tanggal)
//        var itemPenulis: TextView= itemView.findViewById(R.id.item_penulis)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            listener.onItemClick(position)

        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }


}