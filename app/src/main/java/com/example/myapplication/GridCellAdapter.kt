package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class GridCellAdapter(private val context: Context, month: Int, year: Int) :
    BaseAdapter() {

    private var list: MutableList<DateOfMonth> = ArrayList()

    private val daysOfMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    private var daysInMonth = 0
    private var currentDayOfMonth = 0
    private var currentWeekDay = 0
    private var tvNumPerDay: TextView? = null
    private var rltContainer: RelativeLayout? = null

    init {
        val listWeek = arrayOf("S", "M", "T", "W", "T", "F", "S").toMutableList()
        for (item in listWeek) {
            val dateOfMonth = DateOfMonth()
            dateOfMonth.dayOfWeekStr = item
            list.add(dateOfMonth)
        }
        val calendar: Calendar = Calendar.getInstance()
        setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH))
        setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK))

        // Print Month

        // Print Month
        printMonth(month, year)

    }

    private fun getNumberOfDaysOfMonth(i: Int): Int {
        return daysOfMonth[i]
    }

    private fun printMonth(mm: Int, yy: Int) {
        val trailingSpaces: Int
        val daysInPrevMonth: Int
        val prevMonth: Int
        val currentMonth = mm -1
        daysInMonth = getNumberOfDaysOfMonth(currentMonth)


        // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
        val cal = GregorianCalendar(yy, currentMonth, 1)
        when (currentMonth) {
            11 -> {
                prevMonth = currentMonth - 1
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth)
            }
            0 -> {
                prevMonth = 11
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth)
            }
            else -> {
                prevMonth = currentMonth - 1
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth)
            }
        }

        val c = Calendar.getInstance()
        c.set(yy,mm-1,1)
        val currentWeekDay = c[Calendar.DAY_OF_WEEK] - 1
        trailingSpaces = currentWeekDay
        if (cal.isLeapYear(cal[Calendar.YEAR]) && mm == 1) {
            ++daysInMonth
        }

        // Trailing Month days
        for (i in 0 until trailingSpaces) {
            val dateOfMonth = DateOfMonth()
            dateOfMonth.numOfMonth = (daysInPrevMonth - trailingSpaces + DAY_OFFSET + i).toString()
            dateOfMonth.enable = false
            list.add(dateOfMonth)
        }

        // Current Month Days
        for (i in 1..daysInMonth) {
            val dateOfMonth = DateOfMonth()
            dateOfMonth.numOfMonth = i.toString()
            dateOfMonth.enable = true
            list.add(dateOfMonth)
        }

        // Leading Month days
        for (i in 0 until 7 - (list.size % 7)) {
            val dateOfMonth = DateOfMonth()
            dateOfMonth.numOfMonth = (i + 1).toString()
            dateOfMonth.enable = false
            list.add(dateOfMonth)
        }
    }

    private fun setCurrentWeekDay(currentWeekDay: Int) {
        this.currentWeekDay = currentWeekDay
    }

    private fun setCurrentDayOfMonth(currentDayOfMonth: Int) {
        this.currentDayOfMonth = currentDayOfMonth
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var row: View? = p1
        if (row == null) {
            val layoutInflater: LayoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            row = layoutInflater.inflate(R.layout.calendar_day_gridcell, p2, false)
        }

        rltContainer = row!!.findViewById(R.id.rltContainer) as RelativeLayout

        tvNumPerDay = row.findViewById(R.id.num_events_per_day) as TextView

        if (p0 < 7) {
            if (p0 == 0) {
                tvNumPerDay!!.setTextColor(Color.RED)
            } else {
                tvNumPerDay!!.setTextColor(Color.BLACK)
            }
            tvNumPerDay!!.text = list[p0].dayOfWeekStr
        } else {
            tvNumPerDay!!.text = list[p0].numOfMonth

            if (list[p0].enable) {
                tvNumPerDay!!.setTextColor(Color.BLACK)
            } else {
                tvNumPerDay!!.setTextColor(Color.LTGRAY)
            }
        }

        return row
    }

    companion object {
        private const val DAY_OFFSET = 1
    }
}