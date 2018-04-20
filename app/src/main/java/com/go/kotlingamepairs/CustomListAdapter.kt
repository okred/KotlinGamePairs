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

        var convertView = view

        val viewHolder: ViewHolder

        if (convertView == null) {

            val inflater = (context).layoutInflater
            convertView = inflater.inflate(R.layout.listview_row_leaderboard, parent, false)

            viewHolder = ViewHolder()
            viewHolder.tvAttempts = convertView!!.findViewById(R.id.text_listview_attempts) as TextView
            viewHolder.tvName = convertView.findViewById(R.id.text_listview_name) as TextView
            viewHolder.tvRating = convertView.findViewById(R.id.text_listview_rating) as TextView

            // store the holder with the view.
            convertView.tag = viewHolder

        } else {
            // AVOID calling expensive findViewById() repeatedly
            viewHolder = convertView.tag as ViewHolder
        }

        //SET attempts, name, rating
        viewHolder.tvAttempts?.text = String.format("%03d", attempts[position])
        viewHolder.tvName?.text = name[position]

        when(attempts[position]){
            in 1..50 ->  {viewHolder.tvRating?.text = "S"
                viewHolder.tvRating?.setTextColor(Color.WHITE)}
            in 50..55 -> {viewHolder.tvRating?.text = "A"
                viewHolder.tvRating?.setTextColor(Color.GREEN)}
            in 55..61 -> {viewHolder.tvRating?.text = "B"
                viewHolder.tvRating?.setTextColor(Color.YELLOW)}
            in 61..69 -> {viewHolder.tvRating?.text = "C"
                viewHolder.tvRating?.setTextColor(Color.parseColor("#FFA500")) }
            in 69..79 -> {viewHolder.tvRating?.text = "D"
                viewHolder.tvRating?.setTextColor(Color.RED)}
            in 79..89 -> {viewHolder.tvRating?.text = "E"
                viewHolder.tvRating?.setTextColor(Color.parseColor("#A52A2A"))}
            else  -> {    viewHolder.tvRating?.text = "F"
                viewHolder.tvRating?.setTextColor(Color.BLACK)
            }
        }

        return convertView
    }

}


