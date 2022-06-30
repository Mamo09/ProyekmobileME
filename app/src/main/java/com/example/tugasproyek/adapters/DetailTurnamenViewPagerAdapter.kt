package com.example.tugasproyek.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tugasproyek.fragments.BracketDetailTurnamenFragment
import com.example.tugasproyek.fragments.OverviewDetailTurnamenFragment
import com.example.tugasproyek.fragments.ResultDetailTurnamenFragment

class DetailTurnamenViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> {
                OverviewDetailTurnamenFragment()
            }
            1->{
                BracketDetailTurnamenFragment()
            }

            else -> {
                ResultDetailTurnamenFragment()
            }
        }
    }


}
