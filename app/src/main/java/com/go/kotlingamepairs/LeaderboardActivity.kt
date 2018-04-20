package com.go.kotlingamepairs

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.content.Intent

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var soundMusicLeaderboard: MediaPlayer

    internal var time: TextView? = null
    private var attemptsVal = -1

    private var nameCustom = "default"
    private var ratingVal = "X"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_leaderboard)

        //LOAD music
        soundMusicLeaderboard = MediaPlayer.create(this, R.raw.music_leaderboard)
        soundMusicLeaderboard.start()
        soundMusicLeaderboard.isLooping

        //GET values from intent
        val intent = intent
        attemptsVal = intent.getIntExtra("EXTRA_attemptCount", -1)
        evaluateRating()

        //CONFIG dialog on Launch
        val inflater = layoutInflater
        @SuppressLint("InflateParams")
        val alertLayout = inflater.inflate(R.layout.dialog_leaderboard_inputscore, null)
        val alert = AlertDialog.Builder(this@LeaderboardActivity)

        alert.setView(alertLayout)

        val rating = alertLayout.findViewById<TextView>(R.id.dialog_leaderboard_inputscore_tv_rating)
        val attempts = alertLayout.findViewById<TextView>(R.id.dialog_leaderboard_inputscore_tv_attempts)
        val inputName = alertLayout.findViewById<EditText>(R.id.dialog_leaderboard_inputscore_et_name)

        rating.text = ratingVal
        attempts.text = String.format("%03d", attemptsVal)

        //CONFIG buttons
        alert.setCancelable(true)
        alert.setNegativeButton("NO") { _, _ ->
            setListPosition(0)
        }
        alert.setPositiveButton("SAVE") { _, _  ->
            nameCustom = inputName.text.toString()
            setListPosition(0)
        }
        val dialog = alert.create()
        dialog.show()
    }

    override fun onResume(){
        super.onResume()

        soundMusicLeaderboard.start()
    }

    override fun onPause(){
        super.onPause()

        soundMusicLeaderboard.pause()
    }

    override fun onBackPressed() {

        soundMusicLeaderboard.stop()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun setListPosition(position: Int) {

        val leaderboardListFragment = fragmentManager.findFragmentById(R.id.fragment_listview) as LeaderboardListFragment
        leaderboardListFragment.listView.smoothScrollToPositionFromTop(position, 0)
    }

    private fun evaluateRating() {
        //Top score - 26 = Perfect, no mistakes
        ratingVal = when(attemptsVal){
            in 1..50 ->  "S"
            in 50..55 -> "A"
            in 55..61 -> "B"
            in 61..69 -> "C"
            in 69..79 -> "D"
            in 79..89 -> "E"
            else  ->     "F"
        }
    }

}