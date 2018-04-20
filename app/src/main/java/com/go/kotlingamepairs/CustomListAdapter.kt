package com.go.kotlingamepairs

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomListAdapter(private val context: Activity, private val name: Array<String>,
                        private val attempts: Array<Int>) : ArrayAdapter<Any>(context, R.layout.listview_row_leaderboard, name) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.listview_row_leaderboard, parent, false)

        val tvRating = rowView.findViewById<View>(R.id.text_listview_rating) as TextView
        val tvName = rowView.findViewById<View>(R.id.text_listview_name) as TextView
        val tvAttempts = rowView.findViewById<View>(R.id.text_listview_attempts) as TextView

        tvName.text = name[position]
        tvAttempts.text = String.format("%03d", attempts[position])

        when(attempts[position]){
            in 1..50 ->  {tvRating.text = "S"
                tvRating.setTextColor(Color.WHITE)}
            in 50..55 -> {tvRating.text = "A"
                tvRating.setTextColor(Color.GREEN)}
            in 55..61 -> {tvRating.text = "B"
                tvRating.setTextColor(Color.YELLOW)}
            in 61..69 -> {tvRating.text = "C"
                tvRating.setTextColor(Color.parseColor("#FFA500")) }
            in 69..79 -> {tvRating.text = "D"
                tvRating.setTextColor(Color.RED)}
            in 79..89 -> {tvRating.text = "E"
                tvRating.setTextColor(Color.parseColor("#A52A2A"))}
            else  -> {    tvRating.text = "F"
                tvRating.setTextColor(Color.BLACK)
            }
        }

        return rowView
    }

}


