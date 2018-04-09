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
        val nameTextField = rowView.findViewById<View>(R.id.textViewNameValueListView) as TextView
        val attemptsTextField = rowView.findViewById<View>(R.id.textViewAttemptsValueListView) as TextView
        val timeTextField = rowView.findViewById<View>(R.id.textViewAttemptsValueListView) as TextView

        //this code sets the values of the objects to values from the arrays
        nameTextField.text = nameArray[position]
        attemptsTextField.text = String.format("%03d", attemptsArray[position])
        timeTextField.text = String.format("%03d", timeArray[position])

        when(attemptsArray[position]){
            in 1..50 ->  //ratingValue = "S"
            nameTextField.setTextColor(Color.WHITE)
            in 50..55 -> // ratingValue = "A"
            nameTextField.setTextColor(Color.GREEN)
            in 55..61 -> //ratingValue = "B"
            nameTextField.setTextColor(Color.YELLOW)
            in 61..69 -> //ratingValue = "C"
            nameTextField.setTextColor(Color.parseColor("#FFA500"))
            in 69..79 -> //ratingValue = "D"
            nameTextField.setTextColor(Color.RED)
            in 79..89 -> //ratingValue = "E"
            nameTextField.setTextColor(Color.parseColor("#A52A2A"))
            else  ->     //ratingValue = "F"
            nameTextField.setTextColor(Color.BLACK)
        }

        return rowView
    }
}


