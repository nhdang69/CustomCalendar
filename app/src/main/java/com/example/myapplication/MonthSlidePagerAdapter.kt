package com.example.myapplication

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.*

class MonthSlidePagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(
        fragmentActivity
    ) {
    private var month = 0
    private var year = 0

    init {
        val calendar: Calendar = Calendar.getInstance()

        month = calendar.get(Calendar.MONTH) + 1
        year = calendar.get(Calendar.YEAR) - 4
    }

    //default 13 year
    override fun getItemCount(): Int {
        return 120
    }

    override fun createFragment(position: Int): Fragment {
        return MonthSlidePageFragment(
            if (position % 12 == 0) 12 else position % 12,
            if (position % 12 == 0) (year + position / 12) -1 else year + position / 12
        )
    }
}