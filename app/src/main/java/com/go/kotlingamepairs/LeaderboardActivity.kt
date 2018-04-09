package com.go.kotlingamepairs
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.EditText
import android.widget.TextView

import kotlinx.android.synthetic.main.dialog_inputscore.*
import kotlinx.android.synthetic.main.listview_row.*
class LeaderboardActivity : AppCompatActivity() {

    internal var time: TextView? = null
    private var attemptsValue = -1
    private var nameCustom = "default"
    private var ratingValue = "X"

    private lateinit var soundBackgroundLeaderboard: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_leaderboard)

        soundBackgroundLeaderboard = MediaPlayer.create(this, R.raw.backgroundmusicleaderboard)
        soundBackgroundLeaderboard.start()
        soundBackgroundLeaderboard.isLooping
        //get the values from intent
        val intent = intent
        attemptsValue = intent.getIntExtra("attemptNum", -1)

        //set up dialog on Launch
        val inflater = layoutInflater
        val alertLayout = inflater.inflate(R.layout.dialog_inputscore, null)
        val alert = AlertDialog.Builder(this@LeaderboardActivity)
        alert.setTitle("Your Results")
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout)
        // time = alertLayout.findViewById(R.id.textViewTimeValue); //potentially further develop for time
//        rating = alertLayout.findViewById(R.id.textViewRatingValue)
        val inputName = alertLayout.findViewById<EditText>(R.id.editTextName)
        val numberAttempts = alertLayout.findViewById<TextView>(R.id.textViewAttemptsValue)

        alert.setCancelable(true)
        alert.setNegativeButton("NO") { _, _ ->
            testSetPositionList(0)
        }

        alert.setPositiveButton("SAVE") { _, _  ->
            nameCustom = inputName.text.toString()
            testSetPositionList(0)
        }
        numberAttempts.text = String.format("%03d", attemptsValue)
        val dialog = alert.create()
        dialog.show()
        evaluateRating()
    }

    private fun evaluateRating() {
        //Top score - 26 Perfect, no mistakes
        ratingValue = when(attemptsValue){
            in 1..50 ->  "S"
            in 50..55 -> "A"
            in 55..61 -> "B"
            in 61..69 -> "C"
            in 69..79 -> "D"
            in 79..89 -> "E"
            else  ->     "F"
        }
    }

    fun testSetPositionList(position: Int) {

        val fragmentListLeaderboard = fragmentManager.findFragmentById(R.id.fragment2) as FragmentListLeaderboard
        fragmentListLeaderboard.listView.smoothScrollToPositionFromTop(position, 0)
    }

}