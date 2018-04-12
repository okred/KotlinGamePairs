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

    private lateinit var previousImageButton: ImageButton
    private lateinit var currentImageButton: ImageButton

    private lateinit var previousImage: ImageView
    private lateinit var currentImage: ImageView

    private lateinit var context: Context

    private var selectCount = 1
    private var attemptCount = 59
    private var pairCount = 25
    private var pairX: Int = 0
    private var pairY = 99
    private var isPair = 0
    private var order = IntArray(52)
    private var deckId = IntArray(52)

    private lateinit var soundCardFirst: MediaPlayer
    private lateinit var soundCardSecondNot: MediaPlayer
    private lateinit var soundCardSecondPair: MediaPlayer
    private lateinit var soundBackground: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        context = this

        //default values not used, error prevent case first click
        currentImageButton = imageButtonr0c0
        previousImageButton = imageButtonr0c0

        setListeners()

        //CREATE cards
        val deck = CardsDeck(context)
        deck.fillDeck()
        deck.shuffleDeck()
        order = deck.orderArray
        deckId = deck.deckIdArray
        assignCardImage()

        //CHECK cards
        Log.d("MainActivity()", "onCreate "+deckId[1])

        //Initializing the media players for the game sounds
        soundCardFirst = MediaPlayer.create(context, R.raw.gunload)
        soundCardSecondNot = MediaPlayer.create(context, R.raw.gunmiss)
        soundCardSecondPair = MediaPlayer.create(context, R.raw.gunboom)
        soundBackground = MediaPlayer.create(context, R.raw.backgroundmusic)
        soundBackground.start()
    }

    private fun setListeners(){

        //ROW 0
        imageButtonr0c0.setOnClickListener(this)
        imageButtonr0c1.setOnClickListener(this)
        imageButtonr0c2.setOnClickListener(this)
        imageButtonr0c3.setOnClickListener(this)
        imageButtonr0c4.setOnClickListener(this)
        imageButtonr0c5.setOnClickListener(this)
        imageButtonr0c6.setOnClickListener(this)
        //ROW 1
        imageButtonr1c0.setOnClickListener(this)
        imageButtonr1c1.setOnClickListener(this)
        imageButtonr1c2.setOnClickListener(this)
        imageButtonr1c3.setOnClickListener(this)
        imageButtonr1c4.setOnClickListener(this)
        imageButtonr1c5.setOnClickListener(this)
        imageButtonr1c6.setOnClickListener(this)
        //ROW 2
        imageButtonr2c0.setOnClickListener(this)
        imageButtonr2c1.setOnClickListener(this)
        imageButtonr2c2.setOnClickListener(this)
        imageButtonr2c3.setOnClickListener(this)
        imageButtonr2c4.setOnClickListener(this)
        imageButtonr2c5.setOnClickListener(this)
        imageButtonr2c6.setOnClickListener(this)
        //ROW 3
        imageButtonr3c0.setOnClickListener(this)
        imageButtonr3c1.setOnClickListener(this)
        imageButtonr3c2.setOnClickListener(this)
        imageButtonr3c3.setOnClickListener(this)
        imageButtonr3c4.setOnClickListener(this)
        imageButtonr3c5.setOnClickListener(this)
        imageButtonr3c6.setOnClickListener(this)
        //ROW 4
        imageButtonr4c0.setOnClickListener(this)
        imageButtonr4c1.setOnClickListener(this)
        imageButtonr4c2.setOnClickListener(this)
        imageButtonr4c3.setOnClickListener(this)
        imageButtonr4c4.setOnClickListener(this)
        imageButtonr4c5.setOnClickListener(this)
        imageButtonr4c6.setOnClickListener(this)
        //ROW 5
        imageButtonr5c0.setOnClickListener(this)
        imageButtonr5c1.setOnClickListener(this)
        imageButtonr5c2.setOnClickListener(this)
        imageButtonr5c3.setOnClickListener(this)
        imageButtonr5c4.setOnClickListener(this)
        imageButtonr5c5.setOnClickListener(this)
        imageButtonr5c6.setOnClickListener(this)
        //ROW 6
        imageButtonr6c0.setOnClickListener(this)
        imageButtonr6c1.setOnClickListener(this)
        imageButtonr6c2.setOnClickListener(this)
        imageButtonr6c3.setOnClickListener(this)
        imageButtonr6c4.setOnClickListener(this)
        imageButtonr6c5.setOnClickListener(this)
        imageButtonr6c6.setOnClickListener(this)
        //ROW 7
        imageButtonr7c0.setOnClickListener(this)
        imageButtonr7c1.setOnClickListener(this)
        imageButtonr7c2.setOnClickListener(this)
    }

    private fun assignCardImage() {

        //ASSIGN images using deckId Integer reference
        //ROW 0
        imageViewr0c0.setImageResource(deckId[0])
        imageViewr0c1.setImageResource(deckId[1])
        imageViewr0c2.setImageResource(deckId[2])
        imageViewr0c3.setImageResource(deckId[3])
        imageViewr0c4.setImageResource(deckId[4])
        imageViewr0c5.setImageResource(deckId[5])
        imageViewr0c6.setImageResource(deckId[6])
        //ROW 1
        imageViewr1c0.setImageResource(deckId[7])
        imageViewr1c1.setImageResource(deckId[8])
        imageViewr1c2.setImageResource(deckId[9])
        imageViewr1c3.setImageResource(deckId[10])
        imageViewr1c4.setImageResource(deckId[11])
        imageViewr1c5.setImageResource(deckId[12])
        imageViewr1c6.setImageResource(deckId[13])
        //ROW 2
        imageViewr2c0.setImageResource(deckId[14])
        imageViewr2c1.setImageResource(deckId[15])
        imageViewr2c2.setImageResource(deckId[16])
        imageViewr2c3.setImageResource(deckId[17])
        imageViewr2c4.setImageResource(deckId[18])
        imageViewr2c5.setImageResource(deckId[19])
        imageViewr2c6.setImageResource(deckId[20])
        //ROW 3
        imageViewr3c0.setImageResource(deckId[21])
        imageViewr3c1.setImageResource(deckId[22])
        imageViewr3c2.setImageResource(deckId[23])
        imageViewr3c3.setImageResource(deckId[24])
        imageViewr3c4.setImageResource(deckId[25])
        imageViewr3c5.setImageResource(deckId[26])
        imageViewr3c6.setImageResource(deckId[27])
        //ROW 4
        imageViewr4c0.setImageResource(deckId[28])
        imageViewr4c1.setImageResource(deckId[39])
        imageViewr4c2.setImageResource(deckId[30])
        imageViewr4c3.setImageResource(deckId[31])
        imageViewr4c4.setImageResource(deckId[32])
        imageViewr4c5.setImageResource(deckId[33])
        imageViewr4c6.setImageResource(deckId[34])
        //ROW 5
        imageViewr5c0.setImageResource(deckId[35])
        imageViewr5c1.setImageResource(deckId[36])
        imageViewr5c2.setImageResource(deckId[37])
        imageViewr5c3.setImageResource(deckId[38])
        imageViewr5c4.setImageResource(deckId[39])
        imageViewr5c5.setImageResource(deckId[40])
        imageViewr5c6.setImageResource(deckId[41])
        //ROW 7
        imageViewr6c0.setImageResource(deckId[42])
        imageViewr6c1.setImageResource(deckId[43])
        imageViewr6c2.setImageResource(deckId[44])
        imageViewr6c3.setImageResource(deckId[45])
        imageViewr6c4.setImageResource(deckId[46])
        imageViewr6c5.setImageResource(deckId[47])
        imageViewr6c6.setImageResource(deckId[48])
        //ROW 8
        imageViewr7c0.setImageResource(deckId[49])
        imageViewr7c1.setImageResource(deckId[50])
        imageViewr7c2.setImageResource(deckId[51])
    }

    override fun onClick(view: View) {

        when (view) {
            imageButtonr0c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 0
                    previousImageButton = imageButtonr0c0
                    previousImage = imageViewr0c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 0
                    currentImageButton = imageButtonr0c0
                    currentImage = imageViewr0c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    imageButtonr0c0.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr0c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 1
                    previousImageButton = imageButtonr0c1
                    previousImage = imageViewr0c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 1
                    currentImageButton = imageButtonr0c1
                    currentImage = imageViewr0c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr0c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 2
                    previousImageButton = imageButtonr0c2
                    previousImage = imageViewr0c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 2
                    currentImageButton = imageButtonr0c2
                    currentImage = imageViewr0c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr0c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 3
                    previousImageButton = imageButtonr0c3
                    previousImage = imageViewr0c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 3

                    currentImageButton = imageButtonr0c3
                    currentImage = imageViewr0c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr0c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 4
                    previousImageButton = imageButtonr0c4
                    previousImage = imageViewr0c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 4
                    currentImageButton = imageButtonr0c4
                    currentImage = imageViewr0c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr0c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 5
                    previousImageButton = imageButtonr0c5
                    previousImage = imageViewr0c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 5
                    currentImageButton = imageButtonr0c5
                    currentImage = imageViewr0c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr0c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 6
                    previousImageButton = imageButtonr0c6
                    previousImage = imageViewr0c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 6
                    currentImageButton = imageButtonr0c6
                    currentImage = imageViewr0c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr1c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 7
                    previousImageButton = imageButtonr1c0
                    previousImage = imageViewr1c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 7
                    currentImageButton = imageButtonr1c0
                    currentImage = imageViewr1c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr1c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 8
                    previousImageButton = imageButtonr1c1
                    previousImage = imageViewr1c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 8
                    currentImageButton = imageButtonr1c1
                    currentImage = imageViewr1c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr1c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 9
                    previousImageButton = imageButtonr1c2
                    previousImage = imageViewr1c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 9
                    currentImageButton = imageButtonr1c2
                    currentImage = imageViewr1c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr1c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 10
                    previousImageButton = imageButtonr1c3
                    previousImage = imageViewr1c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 10
                    currentImageButton = imageButtonr1c3
                    currentImage = imageViewr1c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr1c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 11
                    previousImageButton = imageButtonr1c4
                    previousImage = imageViewr1c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 11
                    currentImageButton = imageButtonr1c4
                    currentImage = imageViewr1c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr1c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 12
                    previousImageButton = imageButtonr1c5
                    previousImage = imageViewr1c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 12
                    currentImageButton = imageButtonr1c5
                    currentImage = imageViewr1c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr1c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 13
                    previousImageButton = imageButtonr1c6
                    previousImage = imageViewr1c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 13
                    currentImageButton = imageButtonr1c6
                    currentImage = imageViewr1c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr2c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 14
                    previousImageButton = imageButtonr2c0
                    previousImage = imageViewr2c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 14
                    currentImageButton = imageButtonr2c0
                    currentImage = imageViewr2c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr2c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 15
                    previousImageButton = imageButtonr2c1
                    previousImage = imageViewr2c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 15
                    currentImageButton = imageButtonr2c1
                    currentImage = imageViewr2c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr2c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 16
                    previousImageButton = imageButtonr2c2
                    previousImage = imageViewr2c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 16
                    currentImageButton = imageButtonr2c2
                    currentImage = imageViewr2c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr2c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 17
                    previousImageButton = imageButtonr2c3
                    previousImage = imageViewr2c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 17
                    currentImageButton = imageButtonr2c3
                    currentImage = imageViewr2c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr2c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 18
                    previousImageButton = imageButtonr2c4
                    previousImage = imageViewr2c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 18
                    currentImageButton = imageButtonr2c4
                    currentImage = imageViewr2c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr2c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 19
                    previousImageButton = imageButtonr2c5
                    previousImage = imageViewr2c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 19
                    currentImageButton = imageButtonr2c5
                    currentImage = imageViewr2c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr2c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 20
                    previousImageButton = imageButtonr2c6
                    previousImage = imageViewr2c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 20
                    currentImageButton = imageButtonr2c6
                    currentImage = imageViewr2c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr3c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 21
                    previousImageButton = imageButtonr3c0
                    previousImage = imageViewr3c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 21
                    currentImageButton = imageButtonr3c0
                    currentImage = imageViewr3c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr3c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 22
                    previousImageButton = imageButtonr3c1
                    previousImage = imageViewr3c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 22
                    currentImageButton = imageButtonr3c1
                    currentImage = imageViewr3c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr3c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 23
                    previousImageButton = imageButtonr3c2
                    previousImage = imageViewr3c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 23
                    currentImageButton = imageButtonr3c2
                    currentImage = imageViewr3c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr3c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 24
                    previousImageButton = imageButtonr3c3
                    previousImage = imageViewr3c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 24
                    currentImageButton = imageButtonr3c3
                    currentImage = imageViewr3c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr3c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 25
                    previousImageButton = imageButtonr3c4
                    previousImage = imageViewr3c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 25
                    currentImageButton = imageButtonr3c4
                    currentImage = imageViewr3c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr3c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 26
                    previousImageButton = imageButtonr3c5
                    previousImage = imageViewr3c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 26
                    currentImageButton = imageButtonr3c5
                    currentImage = imageViewr3c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr3c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 27
                    previousImageButton = imageButtonr3c6
                    previousImage = imageViewr3c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 27
                    currentImageButton = imageButtonr3c6
                    currentImage = imageViewr3c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr4c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 28
                    previousImageButton = imageButtonr4c0
                    previousImage = imageViewr4c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 28
                    currentImageButton = imageButtonr4c0
                    currentImage = imageViewr4c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr4c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 29
                    previousImageButton = imageButtonr4c1
                    previousImage = imageViewr4c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 29
                    currentImageButton = imageButtonr4c1
                    currentImage = imageViewr4c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr4c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 30
                    previousImageButton = imageButtonr4c2
                    previousImage = imageViewr4c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 30
                    currentImageButton = imageButtonr4c2
                    currentImage = imageViewr4c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr4c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 31
                    previousImageButton = imageButtonr4c3
                    previousImage = imageViewr4c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 31
                    currentImageButton = imageButtonr4c3
                    currentImage = imageViewr4c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr4c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 32
                    previousImageButton = imageButtonr4c4
                    previousImage = imageViewr4c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 32
                    currentImageButton = imageButtonr4c4
                    currentImage = imageViewr4c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr4c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 33
                    previousImageButton = imageButtonr4c5
                    previousImage = imageViewr4c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 33
                    currentImageButton = imageButtonr4c5
                    currentImage = imageViewr4c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr4c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 34
                    previousImageButton = imageButtonr4c6
                    previousImage = imageViewr4c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 34
                    currentImageButton = imageButtonr4c6
                    currentImage = imageViewr4c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr5c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 35
                    previousImageButton = imageButtonr5c0
                    previousImage = imageViewr5c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 35
                    currentImageButton = imageButtonr5c0
                    currentImage = imageViewr5c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr5c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 36
                    previousImageButton = imageButtonr5c1
                    previousImage = imageViewr5c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 36
                    currentImageButton = imageButtonr5c1
                    currentImage = imageViewr5c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr5c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 37
                    previousImageButton = imageButtonr5c2
                    previousImage = imageViewr5c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 37
                    currentImageButton = imageButtonr5c2
                    currentImage = imageViewr5c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr5c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 38
                    previousImageButton = imageButtonr5c3
                    previousImage = imageViewr5c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 38
                    currentImageButton = imageButtonr5c3
                    currentImage = imageViewr5c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr5c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 39
                    previousImageButton = imageButtonr5c4
                    previousImage = imageViewr5c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 39
                    currentImageButton = imageButtonr5c4
                    currentImage = imageViewr5c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr5c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 40
                    previousImageButton = imageButtonr5c5
                    previousImage = imageViewr5c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 40
                    currentImageButton = imageButtonr5c5
                    currentImage = imageViewr5c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr5c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 41
                    previousImageButton = imageButtonr5c6
                    previousImage = imageViewr5c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 41
                    currentImageButton = imageButtonr5c6
                    currentImage = imageViewr5c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr6c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 42
                    previousImageButton = imageButtonr6c0
                    previousImage = imageViewr6c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 42
                    currentImageButton = imageButtonr6c0
                    currentImage = imageViewr6c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr6c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 43
                    previousImageButton = imageButtonr6c1
                    previousImage = imageViewr6c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 43
                    currentImageButton = imageButtonr6c1
                    currentImage = imageViewr6c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr6c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 44
                    previousImageButton = imageButtonr6c2
                    previousImage = imageViewr6c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 44
                    currentImageButton = imageButtonr6c2
                    currentImage = imageViewr6c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr6c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 45
                    previousImageButton = imageButtonr6c3
                    previousImage = imageViewr6c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 45
                    currentImageButton = imageButtonr6c3
                    currentImage = imageViewr6c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr6c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 46
                    previousImageButton = imageButtonr6c4
                    previousImage = imageViewr6c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 46
                    currentImageButton = imageButtonr6c4
                    currentImage = imageViewr6c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr6c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 47
                    previousImageButton = imageButtonr6c5
                    previousImage = imageViewr6c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 47
                    currentImageButton = imageButtonr6c5
                    currentImage = imageViewr6c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr6c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 48
                    previousImageButton = imageButtonr6c6
                    previousImage = imageViewr6c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 48
                    currentImageButton = imageButtonr6c6
                    currentImage = imageViewr6c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr7c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 49
                    previousImageButton = imageButtonr7c0
                    previousImage = imageViewr7c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 49
                    currentImageButton = imageButtonr7c0
                    currentImage = imageViewr7c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr7c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 50
                    previousImageButton = imageButtonr7c1
                    previousImage = imageViewr7c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 50
                    currentImageButton = imageButtonr7c1
                    currentImage = imageViewr7c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imageButtonr7c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 51
                    previousImageButton = imageButtonr7c2
                    previousImage = imageViewr7c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 51
                    currentImageButton = imageButtonr7c2
                    currentImage = imageViewr7c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }

            else -> Toast.makeText(this, "Something Else", Toast.LENGTH_LONG).show()
        }
    }

    private fun delay(pair: Int) {
        val handler = Handler()
        when (pair) {
            0 //Not Pair
            -> {
                handler.postDelayed({
                    // Do this after delay (e.g. 2000 = 2 seconds)
                    currentImageButton.setImageResource(R.drawable.cardback)
                    previousImageButton.setImageResource(R.drawable.cardback)
                    selectCount = 1 //may be redundant, just to make sure
                    attemptCount += 1
                }, 2000)
                Toast.makeText(this, "Not Pair ", Toast.LENGTH_SHORT).show()
            }
            1 //Pair
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

                        soundBackground.stop()
                        val intent = Intent(this@MainActivity, LeaderboardActivity::class.java)
                        intent.putExtra("attemptNum", attemptCount)
                        startActivity(intent)
                        finish()

                    }
                }, 1500)
                Toast.makeText(this, "Pair", Toast.LENGTH_SHORT).show()
            }
            else -> Toast.makeText(this, "delay not work", Toast.LENGTH_SHORT).show()
        }
    }

    private fun evaluatePair() {
        Log.d("MainActivity() evaluatePair() ", " order[$pairX] order[$pairY] ")

        //FIX error case: self pair

        if (pairX == pairY) {
            Toast.makeText(this, "Can not pair a card with itself!", Toast.LENGTH_SHORT).show()
            isPair = 0
        } else if (order[pairX] == order[pairY]) {
            isPair = 1 //IS PAIR
            soundCardSecondPair.start()
        } else {
            isPair = 0 //NOT PAIR
            soundCardSecondNot.start()
        }
    }

}