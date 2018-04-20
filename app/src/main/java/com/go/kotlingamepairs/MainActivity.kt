package com.go.kotlingamepairs

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
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

    private lateinit var previousImageButton: ImageButton
    private lateinit var currentImageButton: ImageButton
    private lateinit var previousImage: ImageView
    private lateinit var currentImage: ImageView

    private lateinit var context: Context

    private var deckOrder = IntArray(52)
    private var deckId = IntArray(52)

    private var selectCount = 1
    private var attemptCount = 59
    private var pairCount = 25
    private var pairId1: Int = 0
    private var pairId2 = 99
    private var isPair = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        context = this

        //FIX error case: first click. default values are not used.
        currentImageButton = imagebutton_r0c0
        previousImageButton = imagebutton_r0c0

        setListeners()

        //CREATE cards
        val deck = Cards(context)
        deck.fillDeck()
        deck.shuffleDeck()
        deckOrder = deck.order
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
            imagebutton_r0c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 0
                    previousImageButton = imagebutton_r0c0
                    previousImage = image_r0c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 0
                    currentImageButton = imagebutton_r0c0
                    currentImage = image_r0c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    imagebutton_r0c0.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 1
                    previousImageButton = imagebutton_r0c1
                    previousImage = image_r0c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 1
                    currentImageButton = imagebutton_r0c1
                    currentImage = image_r0c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 2
                    previousImageButton = imagebutton_r0c2
                    previousImage = image_r0c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 2
                    currentImageButton = imagebutton_r0c2
                    currentImage = image_r0c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 3
                    previousImageButton = imagebutton_r0c3
                    previousImage = image_r0c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 3

                    currentImageButton = imagebutton_r0c3
                    currentImage = image_r0c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 4
                    previousImageButton = imagebutton_r0c4
                    previousImage = image_r0c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 4
                    currentImageButton = imagebutton_r0c4
                    currentImage = image_r0c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 5
                    previousImageButton = imagebutton_r0c5
                    previousImage = image_r0c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 5
                    currentImageButton = imagebutton_r0c5
                    currentImage = image_r0c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 6
                    previousImageButton = imagebutton_r0c6
                    previousImage = image_r0c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 6
                    currentImageButton = imagebutton_r0c6
                    currentImage = image_r0c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 7
                    previousImageButton = imagebutton_r1c0
                    previousImage = image_r1c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 7
                    currentImageButton = imagebutton_r1c0
                    currentImage = image_r1c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 8
                    previousImageButton = imagebutton_r1c1
                    previousImage = image_r1c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 8
                    currentImageButton = imagebutton_r1c1
                    currentImage = image_r1c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 9
                    previousImageButton = imagebutton_r1c2
                    previousImage = image_r1c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 9
                    currentImageButton = imagebutton_r1c2
                    currentImage = image_r1c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 10
                    previousImageButton = imagebutton_r1c3
                    previousImage = image_r1c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 10
                    currentImageButton = imagebutton_r1c3
                    currentImage = image_r1c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 11
                    previousImageButton = imagebutton_r1c4
                    previousImage = image_r1c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 11
                    currentImageButton = imagebutton_r1c4
                    currentImage = image_r1c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 12
                    previousImageButton = imagebutton_r1c5
                    previousImage = image_r1c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 12
                    currentImageButton = imagebutton_r1c5
                    currentImage = image_r1c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 13
                    previousImageButton = imagebutton_r1c6
                    previousImage = image_r1c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 13
                    currentImageButton = imagebutton_r1c6
                    currentImage = image_r1c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 14
                    previousImageButton = imagebutton_r2c0
                    previousImage = image_r2c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 14
                    currentImageButton = imagebutton_r2c0
                    currentImage = image_r2c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 15
                    previousImageButton = imagebutton_r2c1
                    previousImage = image_r2c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 15
                    currentImageButton = imagebutton_r2c1
                    currentImage = image_r2c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 16
                    previousImageButton = imagebutton_r2c2
                    previousImage = image_r2c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 16
                    currentImageButton = imagebutton_r2c2
                    currentImage = image_r2c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 17
                    previousImageButton = imagebutton_r2c3
                    previousImage = image_r2c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 17
                    currentImageButton = imagebutton_r2c3
                    currentImage = image_r2c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 18
                    previousImageButton = imagebutton_r2c4
                    previousImage = image_r2c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 18
                    currentImageButton = imagebutton_r2c4
                    currentImage = image_r2c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 19
                    previousImageButton = imagebutton_r2c5
                    previousImage = image_r2c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 19
                    currentImageButton = imagebutton_r2c5
                    currentImage = image_r2c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 20
                    previousImageButton = imagebutton_r2c6
                    previousImage = image_r2c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 20
                    currentImageButton = imagebutton_r2c6
                    currentImage = image_r2c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 21
                    previousImageButton = imagebutton_r3c0
                    previousImage = image_r3c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 21
                    currentImageButton = imagebutton_r3c0
                    currentImage = image_r3c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 22
                    previousImageButton = imagebutton_r3c1
                    previousImage = image_r3c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 22
                    currentImageButton = imagebutton_r3c1
                    currentImage = image_r3c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 23
                    previousImageButton = imagebutton_r3c2
                    previousImage = image_r3c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 23
                    currentImageButton = imagebutton_r3c2
                    currentImage = image_r3c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 24
                    previousImageButton = imagebutton_r3c3
                    previousImage = image_r3c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 24
                    currentImageButton = imagebutton_r3c3
                    currentImage = image_r3c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 25
                    previousImageButton = imagebutton_r3c4
                    previousImage = image_r3c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 25
                    currentImageButton = imagebutton_r3c4
                    currentImage = image_r3c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 26
                    previousImageButton = imagebutton_r3c5
                    previousImage = image_r3c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 26
                    currentImageButton = imagebutton_r3c5
                    currentImage = image_r3c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 27
                    previousImageButton = imagebutton_r3c6
                    previousImage = image_r3c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 27
                    currentImageButton = imagebutton_r3c6
                    currentImage = image_r3c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 28
                    previousImageButton = imagebutton_r4c0
                    previousImage = image_r4c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 28
                    currentImageButton = imagebutton_r4c0
                    currentImage = image_r4c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 29
                    previousImageButton = imagebutton_r4c1
                    previousImage = image_r4c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 29
                    currentImageButton = imagebutton_r4c1
                    currentImage = image_r4c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 30
                    previousImageButton = imagebutton_r4c2
                    previousImage = image_r4c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 30
                    currentImageButton = imagebutton_r4c2
                    currentImage = image_r4c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 31
                    previousImageButton = imagebutton_r4c3
                    previousImage = image_r4c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 31
                    currentImageButton = imagebutton_r4c3
                    currentImage = image_r4c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 32
                    previousImageButton = imagebutton_r4c4
                    previousImage = image_r4c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 32
                    currentImageButton = imagebutton_r4c4
                    currentImage = image_r4c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 33
                    previousImageButton = imagebutton_r4c5
                    previousImage = image_r4c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 33
                    currentImageButton = imagebutton_r4c5
                    currentImage = image_r4c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 34
                    previousImageButton = imagebutton_r4c6
                    previousImage = image_r4c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 34
                    currentImageButton = imagebutton_r4c6
                    currentImage = image_r4c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 35
                    previousImageButton = imagebutton_r5c0
                    previousImage = image_r5c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 35
                    currentImageButton = imagebutton_r5c0
                    currentImage = image_r5c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 36
                    previousImageButton = imagebutton_r5c1
                    previousImage = image_r5c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 36
                    currentImageButton = imagebutton_r5c1
                    currentImage = image_r5c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 37
                    previousImageButton = imagebutton_r5c2
                    previousImage = image_r5c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 37
                    currentImageButton = imagebutton_r5c2
                    currentImage = image_r5c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 38
                    previousImageButton = imagebutton_r5c3
                    previousImage = image_r5c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 38
                    currentImageButton = imagebutton_r5c3
                    currentImage = image_r5c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 39
                    previousImageButton = imagebutton_r5c4
                    previousImage = image_r5c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 39
                    currentImageButton = imagebutton_r5c4
                    currentImage = image_r5c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 40
                    previousImageButton = imagebutton_r5c5
                    previousImage = image_r5c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 40
                    currentImageButton = imagebutton_r5c5
                    currentImage = image_r5c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 41
                    previousImageButton = imagebutton_r5c6
                    previousImage = image_r5c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 41
                    currentImageButton = imagebutton_r5c6
                    currentImage = image_r5c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 42
                    previousImageButton = imagebutton_r6c0
                    previousImage = image_r6c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 42
                    currentImageButton = imagebutton_r6c0
                    currentImage = image_r6c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 43
                    previousImageButton = imagebutton_r6c1
                    previousImage = image_r6c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 43
                    currentImageButton = imagebutton_r6c1
                    currentImage = image_r6c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 44
                    previousImageButton = imagebutton_r6c2
                    previousImage = image_r6c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 44
                    currentImageButton = imagebutton_r6c2
                    currentImage = image_r6c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 45
                    previousImageButton = imagebutton_r6c3
                    previousImage = image_r6c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 45
                    currentImageButton = imagebutton_r6c3
                    currentImage = image_r6c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 46
                    previousImageButton = imagebutton_r6c4
                    previousImage = image_r6c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 46
                    currentImageButton = imagebutton_r6c4
                    currentImage = image_r6c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 47
                    previousImageButton = imagebutton_r6c5
                    previousImage = image_r6c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 47
                    currentImageButton = imagebutton_r6c5
                    currentImage = image_r6c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 48
                    previousImageButton = imagebutton_r6c6
                    previousImage = image_r6c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 48
                    currentImageButton = imagebutton_r6c6
                    currentImage = image_r6c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r7c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 49
                    previousImageButton = imagebutton_r7c0
                    previousImage = image_r7c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 49
                    currentImageButton = imagebutton_r7c0
                    currentImage = image_r7c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r7c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 50
                    previousImageButton = imagebutton_r7c1
                    previousImage = image_r7c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 50
                    currentImageButton = imagebutton_r7c1
                    currentImage = image_r7c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3  //NOT READY. Runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r7c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairId1 = 51
                    previousImageButton = imagebutton_r7c2
                    previousImage = image_r7c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2  //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairId2 = 51
                    currentImageButton = imagebutton_r7c2
                    currentImage = image_r7c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
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
                    currentImageButton.setImageResource(R.drawable.btn_cardback)
                    previousImageButton.setImageResource(R.drawable.btn_cardback)
                    selectCount = 1 //may be redundant, just to make sure
                    attemptCount += 1
                }, 2000)
                Toast.makeText(this, "Not Pair ", Toast.LENGTH_SHORT).show()
            }
            true //Pair
            -> {
                handler.postDelayed({
                    // Do something after 5s = 5000ms
                    currentImage.setImageResource(0)
                    previousImage.setImageResource(0)
                    currentImageButton.setImageResource(0)
                    previousImageButton.setImageResource(0)
                    previousImageButton.isClickable = false
                    currentImageButton.isClickable = false
                    selectCount = 1 //may be redundant, just to make sure
                    attemptCount += 1
                    pairCount += 1
                    if (pairCount == 26) {
                        //last pair picked succesfully, launch leaderboard activity

                        soundMusic.stop()
                        val intent = Intent(this@MainActivity, LeaderboardActivity::class.java)
                        intent.putExtra("EXTRA_attemptCount", attemptCount)
                        startActivity(intent)
                        finish()

                    }
                }, 1500)
                Toast.makeText(this, "Pair", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun evaluatePair() {

        if (pairId1 == pairId2) {
            //FIX error case: self pair
            Toast.makeText(this, "Can not pair a card with itself!", Toast.LENGTH_SHORT).show()
            isPair = false
        } else if (deckOrder[pairId1] == deckOrder[pairId2]) {
            isPair = true
            soundCardSecondPair.start()
        } else {
            isPair = false
            soundCardSecondNot.start()
        }
    }

}