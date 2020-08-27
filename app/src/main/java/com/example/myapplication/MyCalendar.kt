package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.view_my_calendar.view.*
import java.util.*


class MyCalendar(context: Context, attributeSet: AttributeSet) : ConstraintLayout(
    context,
    attributeSet
) {
    init {
        val layoutInflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.view_my_calendar, this, true)


        val today: Calendar = Calendar.getInstance()
        val birthDay: Calendar = Calendar.getInstance()
        birthDay.set(Calendar.YEAR, today.get(Calendar.YEAR) - 5)
        birthDay.set(Calendar.MONTH, today.get(Calendar.MONTH))
        val yearsInBetween = (today[Calendar.YEAR]
                - birthDay[Calendar.YEAR])
        val monthsDiff = (today[Calendar.MONTH]
                - birthDay[Calendar.MONTH])
        val ageInMonths = yearsInBetween * 12 + monthsDiff.toLong()
        vpCalendar.adapter = MonthSlidePagerAdapter(context as FragmentActivity)
        vpCalendar.currentItem = ageInMonths.toInt() - 4
    }
}