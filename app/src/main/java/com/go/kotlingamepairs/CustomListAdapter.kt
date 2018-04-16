package com.go.kotlingamepairs

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import kotlinx.android.synthetic.main.listview_row_leaderboard.*

class CustomListAdapter(private val context: Activity, private val nameArray: Array<String>,
                        private val attemptsArray: Array<Int>) : ArrayAdapter<Any>(context, R.layout.listview_row_leaderboard, nameArray) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.listview_row_leaderboard, null, true)

        //GET references to objects in listview_row_leaderboard.xml
        var tvRating = rowView.findViewById<View>(R.id.text_listview_rating_value) as TextView
        val tvName = rowView.findViewById<View>(R.id.text_listview_name_value) as TextView
        val tvAttempts = rowView.findViewById<View>(R.id.text_listview_attempts_value) as TextView

        //this code sets the values of the objects to values from the arrays
        tvName.text = nameArray[position]
        tvAttempts.text = String.format("%03d", attemptsArray[position])

        when(attemptsArray[position]){
            in 1..50 ->  tvRating.text = "S"
            in 50..55 -> tvRating.text = "A"
            in 55..61 -> tvRating.text = "B"
            in 61..69 -> tvRating.text = "C"
            in 69..79 -> tvRating.text = "D"
            in 79..89 -> tvRating.text = "E"
            else  -> {
                tvRating.text = "F"
                tvRating.setTextColor(Color.BLACK)
            }
        }

        //TODO - put two when statements into one - avoid error "Type Inference failed"
        when(attemptsArray[position]){
            in 0..50 ->
            tvRating.setTextColor(Color.WHITE)
            in 51..55 ->
            tvRating.setTextColor(Color.GREEN)
            in 56..61 ->
            tvRating.setTextColor(Color.YELLOW)
            in 62..69 ->
            tvRating.setTextColor(Color.parseColor("#FFA500"))
            in 70..79 ->
            tvRating.setTextColor(Color.RED)
            in 80..89 ->
            tvRating.setTextColor(Color.parseColor("#A52A2A"))
            else  -> {
                tvRating.setTextColor(Color.BLACK)
            }
        }

        return rowView
    }
}


