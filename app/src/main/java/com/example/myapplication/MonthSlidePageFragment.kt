package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_month_slide_page.*

class MonthSlidePageFragment(private val month: Int,private val year: Int) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_month_slide_page, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle.text = "$month/$year"
        setGridCellAdapterToDate(month, year)
    }

    private fun setGridCellAdapterToDate(month: Int, year: Int) {
        if (context != null) {
            val adapter = GridCellAdapter(context!!, month, year)
            adapter.notifyDataSetChanged()
            gvCalendar.adapter = adapter
        }
    }
}