package com.go.kotlingamepairs

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.content.Intent

class LeaderboardActivity : AppCompatActivity() {

    internal var time: TextView? = null
    private var attemptsValue = -1
    private var nameCustom = "default"
    private var ratingValue = "X"

    private lateinit var soundBackgroundLeaderboard: MediaPlayer

    override fun onPause(){
        super.onPause()

        soundBackgroundLeaderboard.pause()
    }

    override fun onResume(){
        super.onResume()

        soundBackgroundLeaderboard.start()
    }

    override fun onBackPressed() {

        soundBackgroundLeaderboard.stop()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_leaderboard)

        //LOAD music
        soundBackgroundLeaderboard = MediaPlayer.create(this, R.raw.music_background_leaderboard)
        soundBackgroundLeaderboard.start()
        soundBackgroundLeaderboard.isLooping

        //GET values from intent
        val intent = intent
        attemptsValue = intent.getIntExtra("EXTRA_attemptCount", -1)
        evaluateRating()

        //CONFIG dialog on Launch
        val inflater = layoutInflater
        val alertLayout = inflater.inflate(R.layout.dialog_leaderboard_inputscore, null)
        val alert = AlertDialog.Builder(this@LeaderboardActivity)

       // alert.setTitle("Your Results")
        alert.setView(alertLayout)

        //potentially further develop for time
        val rating = alertLayout.findViewById<TextView>(R.id.textViewRatingValue)
        val numberAttempts = alertLayout.findViewById<TextView>(R.id.textViewAttemptsValue)
        val inputName = alertLayout.findViewById<EditText>(R.id.editTextName)
        rating.text = ratingValue
        numberAttempts.text = String.format("%03d", attemptsValue)

        //CONFIG buttons
        alert.setCancelable(true)
        alert.setNegativeButton("NO") { _, _ ->
            testSetPositionList(0)
        }
        alert.setPositiveButton("SAVE") { _, _  ->
            nameCustom = inputName.text.toString()
            testSetPositionList(0)
        }
        val dialog = alert.create()
        dialog.show()
    }

//    private fun showAlertDialog(attemptsValue : Int) {
//        Log.d("LeaderboardaActivity", "showAlertDialog()")
//        val fm = supportFragmentManager
//        val alertDialog = NameInputDialogFragment.newInstance(-1)
//        val bundle = Bundle()
//        bundle.putInt("attemptsValue", attemptsValue)
//        alertDialog.arguments = bundle
//        alertDialog.show(fm, "fragment_alert")
//
//    }
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

        val fragmentListLeaderboard = fragmentManager.findFragmentById(R.id.fragment_listview) as LeaderboardListFragment
        fragmentListLeaderboard.listView.smoothScrollToPositionFromTop(position, 0)
    }

}