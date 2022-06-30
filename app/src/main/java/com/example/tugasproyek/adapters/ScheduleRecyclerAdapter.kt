package com.example.tugasproyek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasproyek.R
import com.example.tugasproyek.dataclass.scheduleDataClass
import com.example.tugasproyek.fragments.ScheduleFragment

class ScheduleRecyclerAdapter(private var scheduleList:List<scheduleDataClass>, private var listener: ScheduleFragment): RecyclerView.Adapter<ScheduleRecyclerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.schedule_card_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = scheduleList[position]

        holder.itemSchedule.text = currentItem.dates
        holder.itemTeam1.text = currentItem.team1
        holder.itemteam2.text = currentItem.team2
        holder.itemTour.text = currentItem.categories
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var itemSchedule: TextView = itemView.findViewById(R.id.itemJadwal)
        var itemTeam1: TextView = itemView.findViewById(R.id.itemTeam1)
        var itemteam2: TextView = itemView.findViewById(R.id.itemTeam1)
        var itemTour: TextView = itemView.findViewById(R.id.itemTour)

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