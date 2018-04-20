package com.go.kotlingamepairs

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var soundCardFirst: MediaPlayer
    private lateinit var soundCardSecondNot: MediaPlayer
    private lateinit var soundCardSecondPair: MediaPlayer
    private lateinit var soundMusic: MediaPlayer

    private lateinit var ibPrevious: ImageButton
    private lateinit var ibCurrent: ImageButton
    private lateinit var ivPrevious: ImageView
    private lateinit var ivCurrent: ImageView

    private lateinit var context: Context

    private var deckOrder = IntArray(52)
    private var deckId = IntArray(52)

    private var countSelect = 1
    private var countAttempt = 59
    private var countPair = 25
    private var idPair1: Int = 0
    private var idPair2 = 99
    private var isPair = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        context = this

        //FIX error case: first click. default values are not used.
        ibCurrent = imagebutton_r0c0
        ibPrevious = imagebutton_r0c0

        setListeners()

        //CREATE cards
        val deck = Cards(context)
        deck.fillDeck()
        deck.shuffleDeck()
        deckOrder = deck.deckOrder
        deckId = deck.deckId
        assignCardImage()

        //CONFIG sounds
        soundCardFirst = MediaPlayer.create(context, R.raw.sfx_gun_load)
        soundCardSecondNot = MediaPlayer.create(context, R.raw.sfx_gun_miss)
        soundCardSecondPair = MediaPlayer.create(context, R.raw.sfx_gun_boom)
        soundMusic = MediaPlayer.create(context, R.raw.music_main)
        soundMusic.start()
    }

    override fun onResume(){
        super.onResume()

        soundMusic.start()

    }

    override fun onPause(){
        super.onPause()

        soundMusic.pause()

    }

    override fun onClick(view: View) {

        when (view) {
            imagebutton_r0c0 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 0
                    ibPrevious = imagebutton_r0c0
                    ivPrevious = image_r0c0
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 0
                    ibCurrent = imagebutton_r0c0
                    ivCurrent = image_r0c0
                    ibCurrent.setImageResource(android.R.color.transparent)
                    imagebutton_r0c0.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c1 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 1
                    ibPrevious = imagebutton_r0c1
                    ivPrevious = image_r0c1
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 1
                    ibCurrent = imagebutton_r0c1
                    ivCurrent = image_r0c1
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c2 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 2
                    ibPrevious = imagebutton_r0c2
                    ivPrevious = image_r0c2
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 2
                    ibCurrent = imagebutton_r0c2
                    ivCurrent = image_r0c2
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c3 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 3
                    ibPrevious = imagebutton_r0c3
                    ivPrevious = image_r0c3
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 3

                    ibCurrent = imagebutton_r0c3
                    ivCurrent = image_r0c3
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c4 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 4
                    ibPrevious = imagebutton_r0c4
                    ivPrevious = image_r0c4
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 4
                    ibCurrent = imagebutton_r0c4
                    ivCurrent = image_r0c4
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c5 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 5
                    ibPrevious = imagebutton_r0c5
                    ivPrevious = image_r0c5
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 5
                    ibCurrent = imagebutton_r0c5
                    ivCurrent = image_r0c5
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c6 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 6
                    ibPrevious = imagebutton_r0c6
                    ivPrevious = image_r0c6
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 6
                    ibCurrent = imagebutton_r0c6
                    ivCurrent = image_r0c6
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c0 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 7
                    ibPrevious = imagebutton_r1c0
                    ivPrevious = image_r1c0
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 7
                    ibCurrent = imagebutton_r1c0
                    ivCurrent = image_r1c0
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c1 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 8
                    ibPrevious = imagebutton_r1c1
                    ivPrevious = image_r1c1
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 8
                    ibCurrent = imagebutton_r1c1
                    ivCurrent = image_r1c1
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c2 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 9
                    ibPrevious = imagebutton_r1c2
                    ivPrevious = image_r1c2
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 9
                    ibCurrent = imagebutton_r1c2
                    ivCurrent = image_r1c2
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c3 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 10
                    ibPrevious = imagebutton_r1c3
                    ivPrevious = image_r1c3
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 10
                    ibCurrent = imagebutton_r1c3
                    ivCurrent = image_r1c3
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c4 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 11
                    ibPrevious = imagebutton_r1c4
                    ivPrevious = image_r1c4
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 11
                    ibCurrent = imagebutton_r1c4
                    ivCurrent = image_r1c4
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c5 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 12
                    ibPrevious = imagebutton_r1c5
                    ivPrevious = image_r1c5
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 12
                    ibCurrent = imagebutton_r1c5
                    ivCurrent = image_r1c5
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c6 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 13
                    ibPrevious = imagebutton_r1c6
                    ivPrevious = image_r1c6
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 13
                    ibCurrent = imagebutton_r1c6
                    ivCurrent = image_r1c6
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c0 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 14
                    ibPrevious = imagebutton_r2c0
                    ivPrevious = image_r2c0
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 14
                    ibCurrent = imagebutton_r2c0
                    ivCurrent = image_r2c0
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c1 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 15
                    ibPrevious = imagebutton_r2c1
                    ivPrevious = image_r2c1
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 15
                    ibCurrent = imagebutton_r2c1
                    ivCurrent = image_r2c1
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c2 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 16
                    ibPrevious = imagebutton_r2c2
                    ivPrevious = image_r2c2
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 16
                    ibCurrent = imagebutton_r2c2
                    ivCurrent = image_r2c2
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c3 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 17
                    ibPrevious = imagebutton_r2c3
                    ivPrevious = image_r2c3
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 17
                    ibCurrent = imagebutton_r2c3
                    ivCurrent = image_r2c3
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c4 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 18
                    ibPrevious = imagebutton_r2c4
                    ivPrevious = image_r2c4
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 18
                    ibCurrent = imagebutton_r2c4
                    ivCurrent = image_r2c4
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c5 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 19
                    ibPrevious = imagebutton_r2c5
                    ivPrevious = image_r2c5
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 19
                    ibCurrent = imagebutton_r2c5
                    ivCurrent = image_r2c5
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c6 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 20
                    ibPrevious = imagebutton_r2c6
                    ivPrevious = image_r2c6
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 20
                    ibCurrent = imagebutton_r2c6
                    ivCurrent = image_r2c6
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c0 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 21
                    ibPrevious = imagebutton_r3c0
                    ivPrevious = image_r3c0
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 21
                    ibCurrent = imagebutton_r3c0
                    ivCurrent = image_r3c0
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c1 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 22
                    ibPrevious = imagebutton_r3c1
                    ivPrevious = image_r3c1
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 22
                    ibCurrent = imagebutton_r3c1
                    ivCurrent = image_r3c1
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c2 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 23
                    ibPrevious = imagebutton_r3c2
                    ivPrevious = image_r3c2
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 23
                    ibCurrent = imagebutton_r3c2
                    ivCurrent = image_r3c2
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c3 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 24
                    ibPrevious = imagebutton_r3c3
                    ivPrevious = image_r3c3
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 24
                    ibCurrent = imagebutton_r3c3
                    ivCurrent = image_r3c3
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c4 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 25
                    ibPrevious = imagebutton_r3c4
                    ivPrevious = image_r3c4
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 25
                    ibCurrent = imagebutton_r3c4
                    ivCurrent = image_r3c4
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c5 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 26
                    ibPrevious = imagebutton_r3c5
                    ivPrevious = image_r3c5
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 26
                    ibCurrent = imagebutton_r3c5
                    ivCurrent = image_r3c5
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c6 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 27
                    ibPrevious = imagebutton_r3c6
                    ivPrevious = image_r3c6
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 27
                    ibCurrent = imagebutton_r3c6
                    ivCurrent = image_r3c6
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c0 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 28
                    ibPrevious = imagebutton_r4c0
                    ivPrevious = image_r4c0
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 28
                    ibCurrent = imagebutton_r4c0
                    ivCurrent = image_r4c0
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c1 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 29
                    ibPrevious = imagebutton_r4c1
                    ivPrevious = image_r4c1
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 29
                    ibCurrent = imagebutton_r4c1
                    ivCurrent = image_r4c1
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c2 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 30
                    ibPrevious = imagebutton_r4c2
                    ivPrevious = image_r4c2
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 30
                    ibCurrent = imagebutton_r4c2
                    ivCurrent = image_r4c2
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c3 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 31
                    ibPrevious = imagebutton_r4c3
                    ivPrevious = image_r4c3
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 31
                    ibCurrent = imagebutton_r4c3
                    ivCurrent = image_r4c3
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c4 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 32
                    ibPrevious = imagebutton_r4c4
                    ivPrevious = image_r4c4
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 32
                    ibCurrent = imagebutton_r4c4
                    ivCurrent = image_r4c4
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c5 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 33
                    ibPrevious = imagebutton_r4c5
                    ivPrevious = image_r4c5
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 33
                    ibCurrent = imagebutton_r4c5
                    ivCurrent = image_r4c5
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c6 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 34
                    ibPrevious = imagebutton_r4c6
                    ivPrevious = image_r4c6
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 34
                    ibCurrent = imagebutton_r4c6
                    ivCurrent = image_r4c6
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c0 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 35
                    ibPrevious = imagebutton_r5c0
                    ivPrevious = image_r5c0
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 35
                    ibCurrent = imagebutton_r5c0
                    ivCurrent = image_r5c0
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c1 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 36
                    ibPrevious = imagebutton_r5c1
                    ivPrevious = image_r5c1
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 36
                    ibCurrent = imagebutton_r5c1
                    ivCurrent = image_r5c1
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c2 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 37
                    ibPrevious = imagebutton_r5c2
                    ivPrevious = image_r5c2
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 37
                    ibCurrent = imagebutton_r5c2
                    ivCurrent = image_r5c2
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c3 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 38
                    ibPrevious = imagebutton_r5c3
                    ivPrevious = image_r5c3
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 38
                    ibCurrent = imagebutton_r5c3
                    ivCurrent = image_r5c3
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c4 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 39
                    ibPrevious = imagebutton_r5c4
                    ivPrevious = image_r5c4
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 39
                    ibCurrent = imagebutton_r5c4
                    ivCurrent = image_r5c4
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c5 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 40
                    ibPrevious = imagebutton_r5c5
                    ivPrevious = image_r5c5
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 40
                    ibCurrent = imagebutton_r5c5
                    ivCurrent = image_r5c5
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c6 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 41
                    ibPrevious = imagebutton_r5c6
                    ivPrevious = image_r5c6
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 41
                    ibCurrent = imagebutton_r5c6
                    ivCurrent = image_r5c6
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c0 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 42
                    ibPrevious = imagebutton_r6c0
                    ivPrevious = image_r6c0
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 42
                    ibCurrent = imagebutton_r6c0
                    ivCurrent = image_r6c0
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c1 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 43
                    ibPrevious = imagebutton_r6c1
                    ivPrevious = image_r6c1
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 43
                    ibCurrent = imagebutton_r6c1
                    ivCurrent = image_r6c1
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c2 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 44
                    ibPrevious = imagebutton_r6c2
                    ivPrevious = image_r6c2
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 44
                    ibCurrent = imagebutton_r6c2
                    ivCurrent = image_r6c2
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c3 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 45
                    ibPrevious = imagebutton_r6c3
                    ivPrevious = image_r6c3
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 45
                    ibCurrent = imagebutton_r6c3
                    ivCurrent = image_r6c3
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c4 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 46
                    ibPrevious = imagebutton_r6c4
                    ivPrevious = image_r6c4
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 46
                    ibCurrent = imagebutton_r6c4
                    ivCurrent = image_r6c4
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c5 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 47
                    ibPrevious = imagebutton_r6c5
                    ivPrevious = image_r6c5
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 47
                    ibCurrent = imagebutton_r6c5
                    ivCurrent = image_r6c5
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c6 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 48
                    ibPrevious = imagebutton_r6c6
                    ivPrevious = image_r6c6
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 48
                    ibCurrent = imagebutton_r6c6
                    ivCurrent = image_r6c6
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r7c0 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 49
                    ibPrevious = imagebutton_r7c0
                    ivPrevious = image_r7c0
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 49
                    ibCurrent = imagebutton_r7c0
                    ivCurrent = image_r7c0
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r7c1 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 50
                    ibPrevious = imagebutton_r7c1
                    ivPrevious = image_r7c1
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 50
                    ibCurrent = imagebutton_r7c1
                    ivCurrent = image_r7c1
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r7c2 -> when (countSelect) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    idPair1 = 51
                    ibPrevious = imagebutton_r7c2
                    ivPrevious = image_r7c2
                    ibPrevious.setImageResource(android.R.color.transparent)
                    countSelect = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    idPair2 = 51
                    ibCurrent = imagebutton_r7c2
                    ivCurrent = image_r7c2
                    ibCurrent.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    countSelect = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
        }
    }

    private fun assignCardImage() {

        //ASSIGN images using deckId Integer reference
        //ROW 0
        image_r0c0.setImageResource(deckId[0])
        image_r0c1.setImageResource(deckId[1])
        image_r0c2.setImageResource(deckId[2])
        image_r0c3.setImageResource(deckId[3])
        image_r0c4.setImageResource(deckId[4])
        image_r0c5.setImageResource(deckId[5])
        image_r0c6.setImageResource(deckId[6])
        //ROW 1
        image_r1c0.setImageResource(deckId[7])
        image_r1c1.setImageResource(deckId[8])
        image_r1c2.setImageResource(deckId[9])
        image_r1c3.setImageResource(deckId[10])
        image_r1c4.setImageResource(deckId[11])
        image_r1c5.setImageResource(deckId[12])
        image_r1c6.setImageResource(deckId[13])
        //ROW 2
        image_r2c0.setImageResource(deckId[14])
        image_r2c1.setImageResource(deckId[15])
        image_r2c2.setImageResource(deckId[16])
        image_r2c3.setImageResource(deckId[17])
        image_r2c4.setImageResource(deckId[18])
        image_r2c5.setImageResource(deckId[19])
        image_r2c6.setImageResource(deckId[20])
        //ROW 3
        image_r3c0.setImageResource(deckId[21])
        image_r3c1.setImageResource(deckId[22])
        image_r3c2.setImageResource(deckId[23])
        image_r3c3.setImageResource(deckId[24])
        image_r3c4.setImageResource(deckId[25])
        image_r3c5.setImageResource(deckId[26])
        image_r3c6.setImageResource(deckId[27])
        //ROW 4
        image_r4c0.setImageResource(deckId[28])
        image_r4c1.setImageResource(deckId[39])
        image_r4c2.setImageResource(deckId[30])
        image_r4c3.setImageResource(deckId[31])
        image_r4c4.setImageResource(deckId[32])
        image_r4c5.setImageResource(deckId[33])
        image_r4c6.setImageResource(deckId[34])
        //ROW 5
        image_r5c0.setImageResource(deckId[35])
        image_r5c1.setImageResource(deckId[36])
        image_r5c2.setImageResource(deckId[37])
        image_r5c3.setImageResource(deckId[38])
        image_r5c4.setImageResource(deckId[39])
        image_r5c5.setImageResource(deckId[40])
        image_r5c6.setImageResource(deckId[41])
        //ROW 7
        image_r6c0.setImageResource(deckId[42])
        image_r6c1.setImageResource(deckId[43])
        image_r6c2.setImageResource(deckId[44])
        image_r6c3.setImageResource(deckId[45])
        image_r6c4.setImageResource(deckId[46])
        image_r6c5.setImageResource(deckId[47])
        image_r6c6.setImageResource(deckId[48])
        //ROW 8
        image_r7c0.setImageResource(deckId[49])
        image_r7c1.setImageResource(deckId[50])
        image_r7c2.setImageResource(deckId[51])
    }

    private fun setListeners(){

        //ROW 0
        imagebutton_r0c0.setOnClickListener(this)
        imagebutton_r0c1.setOnClickListener(this)
        imagebutton_r0c2.setOnClickListener(this)
        imagebutton_r0c3.setOnClickListener(this)
        imagebutton_r0c4.setOnClickListener(this)
        imagebutton_r0c5.setOnClickListener(this)
        imagebutton_r0c6.setOnClickListener(this)
        //ROW 1
        imagebutton_r1c0.setOnClickListener(this)
        imagebutton_r1c1.setOnClickListener(this)
        imagebutton_r1c2.setOnClickListener(this)
        imagebutton_r1c3.setOnClickListener(this)
        imagebutton_r1c4.setOnClickListener(this)
        imagebutton_r1c5.setOnClickListener(this)
        imagebutton_r1c6.setOnClickListener(this)
        //ROW 2
        imagebutton_r2c0.setOnClickListener(this)
        imagebutton_r2c1.setOnClickListener(this)
        imagebutton_r2c2.setOnClickListener(this)
        imagebutton_r2c3.setOnClickListener(this)
        imagebutton_r2c4.setOnClickListener(this)
        imagebutton_r2c5.setOnClickListener(this)
        imagebutton_r2c6.setOnClickListener(this)
        //ROW 3
        imagebutton_r3c0.setOnClickListener(this)
        imagebutton_r3c1.setOnClickListener(this)
        imagebutton_r3c2.setOnClickListener(this)
        imagebutton_r3c3.setOnClickListener(this)
        imagebutton_r3c4.setOnClickListener(this)
        imagebutton_r3c5.setOnClickListener(this)
        imagebutton_r3c6.setOnClickListener(this)
        //ROW 4
        imagebutton_r4c0.setOnClickListener(this)
        imagebutton_r4c1.setOnClickListener(this)
        imagebutton_r4c2.setOnClickListener(this)
        imagebutton_r4c3.setOnClickListener(this)
        imagebutton_r4c4.setOnClickListener(this)
        imagebutton_r4c5.setOnClickListener(this)
        imagebutton_r4c6.setOnClickListener(this)
        //ROW 5
        imagebutton_r5c0.setOnClickListener(this)
        imagebutton_r5c1.setOnClickListener(this)
        imagebutton_r5c2.setOnClickListener(this)
        imagebutton_r5c3.setOnClickListener(this)
        imagebutton_r5c4.setOnClickListener(this)
        imagebutton_r5c5.setOnClickListener(this)
        imagebutton_r5c6.setOnClickListener(this)
        //ROW 6
        imagebutton_r6c0.setOnClickListener(this)
        imagebutton_r6c1.setOnClickListener(this)
        imagebutton_r6c2.setOnClickListener(this)
        imagebutton_r6c3.setOnClickListener(this)
        imagebutton_r6c4.setOnClickListener(this)
        imagebutton_r6c5.setOnClickListener(this)
        imagebutton_r6c6.setOnClickListener(this)
        //ROW 7
        imagebutton_r7c0.setOnClickListener(this)
        imagebutton_r7c1.setOnClickListener(this)
        imagebutton_r7c2.setOnClickListener(this)
    }

    private fun delay(isPair: Boolean) {
        val handler = Handler()
        when (isPair) {
            false //Not Pair
            -> {
                handler.postDelayed({
                    // Do this after delay (e.g. 2000 = 2 seconds)
                    ibCurrent.setImageResource(R.drawable.btn_cardback)
                    ibPrevious.setImageResource(R.drawable.btn_cardback)
                    countSelect = 1 //may be redundant, just to make sure
                    countAttempt += 1
                }, 2000)
                Toast.makeText(this, "Not Pair ", Toast.LENGTH_SHORT).show()
            }
            true //Pair
            -> {
                handler.postDelayed({
                    // Do something after 5s = 5000ms
                    ivCurrent.setImageResource(0)
                    ivPrevious.setImageResource(0)
                    ibCurrent.setImageResource(0)
                    ibPrevious.setImageResource(0)
                    ibPrevious.isClickable = false
                    ibCurrent.isClickable = false
                    countSelect = 1 //may be redundant, just to make sure
                    countAttempt += 1
                    countPair += 1
                    if (countPair == 26) {
                        //last pair picked succesfully, launch leaderboard activity

                        soundMusic.stop()
                        val intent = Intent(this@MainActivity, LeaderboardActivity::class.java)
                        intent.putExtra("EXTRA_attemptCount", countAttempt)
                        startActivity(intent)
                        finish()

                    }
                }, 1500)
                Toast.makeText(this, "Pair", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun evaluatePair() {

        if (idPair1 == idPair2) {
            //FIX error case: self pair
            Toast.makeText(this, "Can not pair a card with itself!", Toast.LENGTH_SHORT).show()
            isPair = false
        } else if (deckOrder[idPair1] == deckOrder[idPair2]) {
            isPair = true
            soundCardSecondPair.start()
        } else {
            isPair = false
            soundCardSecondNot.start()
        }
    }

}