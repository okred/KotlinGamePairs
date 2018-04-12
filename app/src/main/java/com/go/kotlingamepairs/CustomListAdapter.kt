package com.go.kotlingamepairs

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomListAdapter(private val context: Activity, private val nameArray: Array<String>,
                        private val attemptsArray: Array<Int>, private val timeArray: Array<Int>) : ArrayAdapter<Any>(context, R.layout.listview_row, nameArray) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.listview_row, null, true)

        //this code gets references to objects in the listview_row.xml file
        var ratingTextView = rowView.findViewById<View>(R.id.textViewRatingValueListView) as TextView
        val nameTextView = rowView.findViewById<View>(R.id.textViewNameValueListView) as TextView
        val attemptsTextView = rowView.findViewById<View>(R.id.textViewAttemptsValueListView) as TextView
//        val timeTextField = rowView.findViewById<View>(R.id.textViewTimeValueListView) as TextView

        //this code sets the values of the objects to values from the arrays
        nameTextView.text = nameArray[position]
        attemptsTextView.text = String.format("%03d", attemptsArray[position])
//        timeTextField.text = String.format("%03d", timeArray[position])

        when(attemptsArray[position]){
            in 1..50 ->  ratingTextView.text = "S"
            in 50..55 -> ratingTextView.text = "A"
            in 55..61 -> ratingTextView.text = "B"
            in 61..69 -> ratingTextView.text = "C"
            in 69..79 -> ratingTextView.text = "D"
            in 79..89 -> ratingTextView.text = "E"
            else  -> {
                ratingTextView.text = "F"
                ratingTextView.setTextColor(Color.BLACK)
            }

        }

        //TODO - put this into one when statement - avoid error "Type Inference failed"
        when(attemptsArray[position]){
            in 0..50 ->
            ratingTextView.setTextColor(Color.WHITE)
            in 51..55 ->
            ratingTextView.setTextColor(Color.GREEN)
            in 56..61 ->
            ratingTextView.setTextColor(Color.YELLOW)
            in 62..69 ->
            ratingTextView.setTextColor(Color.parseColor("#FFA500"))
            in 70..79 ->
            ratingTextView.setTextColor(Color.RED)
            in 80..89 ->
            ratingTextView.setTextColor(Color.parseColor("#A52A2A"))
            else  -> {
                ratingTextView.setTextColor(Color.BLACK)
            }

        }



        return rowView
    }
}


