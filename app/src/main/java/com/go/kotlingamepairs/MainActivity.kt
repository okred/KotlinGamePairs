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
    private lateinit var soundBackground: MediaPlayer

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
    private var deckOrder = IntArray(52)
    private var deckId = IntArray(52)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        context = this

        //default values not used, error prevent case first click
        currentImageButton = imagebutton_r0c0
        previousImageButton = imagebutton_r0c0

        setListeners()

        //CREATE cards
        val deck = Cards(context)
        deck.fillDeck()
        deck.shuffleDeck()
        deckOrder = deck.orderArray
        deckId = deck.deckIdArray
        assignCardImage()

        //CHECK cards
        Log.d("MainActivity()", "onCreate "+deckId[1])

        //Initializing the media players for the game sounds
        soundCardFirst = MediaPlayer.create(context, R.raw.sfx_gun_load)
        soundCardSecondNot = MediaPlayer.create(context, R.raw.sfx_gun_miss)
        soundCardSecondPair = MediaPlayer.create(context, R.raw.sfx_gun_boom)
        soundBackground = MediaPlayer.create(context, R.raw.music_background_main)
        soundBackground.start()
    }

    override fun onClick(view: View) {

        var location:Int = when (view){

            imagebutton_r0c0 -> 0
            imagebutton_r0c1 -> 1
            imagebutton_r0c2 -> 2
            imagebutton_r0c3 -> 3
            imagebutton_r0c4 -> 4
            imagebutton_r0c5 -> 5
            imagebutton_r0c6 -> 6
            imagebutton_r1c0 -> 7
            imagebutton_r1c1 -> 8
            imagebutton_r1c2 -> 9
            imagebutton_r1c3 -> 10
            imagebutton_r1c4 -> 11
            imagebutton_r1c5 -> 12
            imagebutton_r1c6 -> 13
            imagebutton_r2c0 -> 14
            imagebutton_r2c1 -> 15
            imagebutton_r2c2 -> 16
            imagebutton_r2c3 -> 17
            imagebutton_r2c4 -> 18
            imagebutton_r2c5 -> 19
            imagebutton_r2c6 -> 20
            imagebutton_r3c0 -> 21
            imagebutton_r3c1 -> 22
            imagebutton_r3c2 -> 23
            imagebutton_r3c3 -> 24
            imagebutton_r3c4 -> 25
            imagebutton_r3c5 -> 26
            imagebutton_r3c6 -> 27
            imagebutton_r4c0 -> 28
            imagebutton_r4c1 -> 29
            imagebutton_r4c2 -> 30
            imagebutton_r4c3 -> 31
            imagebutton_r4c4 -> 32
            imagebutton_r4c5 -> 33
            imagebutton_r4c6 -> 34
            imagebutton_r5c0 -> 35
            imagebutton_r5c1 -> 36
            imagebutton_r5c2 -> 37
            imagebutton_r5c3 -> 38
            imagebutton_r5c4 -> 39
            imagebutton_r5c5 -> 40
            imagebutton_r5c6 -> 41
            imagebutton_r6c0 -> 42
            imagebutton_r6c1 -> 43
            imagebutton_r6c2 -> 44
            imagebutton_r6c3 -> 45
            imagebutton_r6c4 -> 46
            imagebutton_r6c5 -> 47
            imagebutton_r6c6 -> 48
            imagebutton_r7c0 -> 49
            imagebutton_r7c1 -> 50
            imagebutton_r7c2 -> 51
            else -> 99
        }
        doCardAction(location)
    }

     fun test(view: View) {

        when (view) {
            imagebutton_r0c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 0
                    previousImageButton = imagebutton_r0c0
                    previousImage = image_r0c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 0
                    currentImageButton = imagebutton_r0c0
                    currentImage = image_r0c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 1
                    previousImageButton = imagebutton_r0c1
                    previousImage = image_r0c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 1
                    currentImageButton = imagebutton_r0c1
                    currentImage = image_r0c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 2
                    previousImageButton = imagebutton_r0c2
                    previousImage = image_r0c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 2
                    currentImageButton = imagebutton_r0c2
                    currentImage = image_r0c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 3
                    previousImageButton = imagebutton_r0c3
                    previousImage = image_r0c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 3

                    currentImageButton = imagebutton_r0c3
                    currentImage = image_r0c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 4
                    previousImageButton = imagebutton_r0c4
                    previousImage = image_r0c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 4
                    currentImageButton = imagebutton_r0c4
                    currentImage = image_r0c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 5
                    previousImageButton = imagebutton_r0c5
                    previousImage = image_r0c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 5
                    currentImageButton = imagebutton_r0c5
                    currentImage = image_r0c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r0c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 6
                    previousImageButton = imagebutton_r0c6
                    previousImage = image_r0c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 6
                    currentImageButton = imagebutton_r0c6
                    currentImage = image_r0c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 7
                    previousImageButton = imagebutton_r1c0
                    previousImage = image_r1c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 7
                    currentImageButton = imagebutton_r1c0
                    currentImage = image_r1c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 8
                    previousImageButton = imagebutton_r1c1
                    previousImage = image_r1c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 8
                    currentImageButton = imagebutton_r1c1
                    currentImage = image_r1c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 9
                    previousImageButton = imagebutton_r1c2
                    previousImage = image_r1c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 9
                    currentImageButton = imagebutton_r1c2
                    currentImage = image_r1c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 10
                    previousImageButton = imagebutton_r1c3
                    previousImage = image_r1c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 10
                    currentImageButton = imagebutton_r1c3
                    currentImage = image_r1c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 11
                    previousImageButton = imagebutton_r1c4
                    previousImage = image_r1c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 11
                    currentImageButton = imagebutton_r1c4
                    currentImage = image_r1c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 12
                    previousImageButton = imagebutton_r1c5
                    previousImage = image_r1c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 12
                    currentImageButton = imagebutton_r1c5
                    currentImage = image_r1c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r1c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 13
                    previousImageButton = imagebutton_r1c6
                    previousImage = image_r1c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 13
                    currentImageButton = imagebutton_r1c6
                    currentImage = image_r1c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 14
                    previousImageButton = imagebutton_r2c0
                    previousImage = image_r2c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 14
                    currentImageButton = imagebutton_r2c0
                    currentImage = image_r2c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 15
                    previousImageButton = imagebutton_r2c1
                    previousImage = image_r2c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 15
                    currentImageButton = imagebutton_r2c1
                    currentImage = image_r2c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 16
                    previousImageButton = imagebutton_r2c2
                    previousImage = image_r2c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 16
                    currentImageButton = imagebutton_r2c2
                    currentImage = image_r2c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 17
                    previousImageButton = imagebutton_r2c3
                    previousImage = image_r2c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 17
                    currentImageButton = imagebutton_r2c3
                    currentImage = image_r2c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 18
                    previousImageButton = imagebutton_r2c4
                    previousImage = image_r2c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 18
                    currentImageButton = imagebutton_r2c4
                    currentImage = image_r2c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 19
                    previousImageButton = imagebutton_r2c5
                    previousImage = image_r2c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 19
                    currentImageButton = imagebutton_r2c5
                    currentImage = image_r2c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r2c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 20
                    previousImageButton = imagebutton_r2c6
                    previousImage = image_r2c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 20
                    currentImageButton = imagebutton_r2c6
                    currentImage = image_r2c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 21
                    previousImageButton = imagebutton_r3c0
                    previousImage = image_r3c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 21
                    currentImageButton = imagebutton_r3c0
                    currentImage = image_r3c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 22
                    previousImageButton = imagebutton_r3c1
                    previousImage = image_r3c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 22
                    currentImageButton = imagebutton_r3c1
                    currentImage = image_r3c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 23
                    previousImageButton = imagebutton_r3c2
                    previousImage = image_r3c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 23
                    currentImageButton = imagebutton_r3c2
                    currentImage = image_r3c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 24
                    previousImageButton = imagebutton_r3c3
                    previousImage = image_r3c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 24
                    currentImageButton = imagebutton_r3c3
                    currentImage = image_r3c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 25
                    previousImageButton = imagebutton_r3c4
                    previousImage = image_r3c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 25
                    currentImageButton = imagebutton_r3c4
                    currentImage = image_r3c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 26
                    previousImageButton = imagebutton_r3c5
                    previousImage = image_r3c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 26
                    currentImageButton = imagebutton_r3c5
                    currentImage = image_r3c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r3c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 27
                    previousImageButton = imagebutton_r3c6
                    previousImage = image_r3c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 27
                    currentImageButton = imagebutton_r3c6
                    currentImage = image_r3c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 28
                    previousImageButton = imagebutton_r4c0
                    previousImage = image_r4c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 28
                    currentImageButton = imagebutton_r4c0
                    currentImage = image_r4c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 29
                    previousImageButton = imagebutton_r4c1
                    previousImage = image_r4c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 29
                    currentImageButton = imagebutton_r4c1
                    currentImage = image_r4c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 30
                    previousImageButton = imagebutton_r4c2
                    previousImage = image_r4c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 30
                    currentImageButton = imagebutton_r4c2
                    currentImage = image_r4c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 31
                    previousImageButton = imagebutton_r4c3
                    previousImage = image_r4c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 31
                    currentImageButton = imagebutton_r4c3
                    currentImage = image_r4c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 32
                    previousImageButton = imagebutton_r4c4
                    previousImage = image_r4c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 32
                    currentImageButton = imagebutton_r4c4
                    currentImage = image_r4c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 33
                    previousImageButton = imagebutton_r4c5
                    previousImage = image_r4c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 33
                    currentImageButton = imagebutton_r4c5
                    currentImage = image_r4c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r4c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 34
                    previousImageButton = imagebutton_r4c6
                    previousImage = image_r4c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 34
                    currentImageButton = imagebutton_r4c6
                    currentImage = image_r4c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 35
                    previousImageButton = imagebutton_r5c0
                    previousImage = image_r5c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 35
                    currentImageButton = imagebutton_r5c0
                    currentImage = image_r5c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 36
                    previousImageButton = imagebutton_r5c1
                    previousImage = image_r5c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 36
                    currentImageButton = imagebutton_r5c1
                    currentImage = image_r5c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 37
                    previousImageButton = imagebutton_r5c2
                    previousImage = image_r5c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 37
                    currentImageButton = imagebutton_r5c2
                    currentImage = image_r5c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 38
                    previousImageButton = imagebutton_r5c3
                    previousImage = image_r5c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 38
                    currentImageButton = imagebutton_r5c3
                    currentImage = image_r5c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 39
                    previousImageButton = imagebutton_r5c4
                    previousImage = image_r5c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 39
                    currentImageButton = imagebutton_r5c4
                    currentImage = image_r5c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 40
                    previousImageButton = imagebutton_r5c5
                    previousImage = image_r5c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 40
                    currentImageButton = imagebutton_r5c5
                    currentImage = image_r5c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r5c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 41
                    previousImageButton = imagebutton_r5c6
                    previousImage = image_r5c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 41
                    currentImageButton = imagebutton_r5c6
                    currentImage = image_r5c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 42
                    previousImageButton = imagebutton_r6c0
                    previousImage = image_r6c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 42
                    currentImageButton = imagebutton_r6c0
                    currentImage = image_r6c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 43
                    previousImageButton = imagebutton_r6c1
                    previousImage = image_r6c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 43
                    currentImageButton = imagebutton_r6c1
                    currentImage = image_r6c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 44
                    previousImageButton = imagebutton_r6c2
                    previousImage = image_r6c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 44
                    currentImageButton = imagebutton_r6c2
                    currentImage = image_r6c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c3 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 45
                    previousImageButton = imagebutton_r6c3
                    previousImage = image_r6c3
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 45
                    currentImageButton = imagebutton_r6c3
                    currentImage = image_r6c3
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c4 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 46
                    previousImageButton = imagebutton_r6c4
                    previousImage = image_r6c4
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 46
                    currentImageButton = imagebutton_r6c4
                    currentImage = image_r6c4
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c5 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 47
                    previousImageButton = imagebutton_r6c5
                    previousImage = image_r6c5
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 47
                    currentImageButton = imagebutton_r6c5
                    currentImage = image_r6c5
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r6c6 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 48
                    previousImageButton = imagebutton_r6c6
                    previousImage = image_r6c6
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 48
                    currentImageButton = imagebutton_r6c6
                    currentImage = image_r6c6
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r7c0 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 49
                    previousImageButton = imagebutton_r7c0
                    previousImage = image_r7c0
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 49
                    currentImageButton = imagebutton_r7c0
                    currentImage = image_r7c0
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r7c1 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 50
                    previousImageButton = imagebutton_r7c1
                    previousImage = image_r7c1
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 50
                    currentImageButton = imagebutton_r7c1
                    currentImage = image_r7c1
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
            imagebutton_r7c2 -> when (selectCount) {

                1  //SELECT 1 of 2 - Set transparent, save prev ImageButton/ImageView, add counter
                -> {
                    pairX = 51
                    previousImageButton = imagebutton_r7c2
                    previousImage = image_r7c2
                    previousImageButton.setImageResource(android.R.color.transparent)
                    selectCount = 2
                    soundCardFirst.start()
                }
                2 //SELECT 2 of 2 -  Set transparent, save curr ImageButton/ImageView, evaluatePair
                -> {
                    pairY = 51
                    currentImageButton = imagebutton_r7c2
                    currentImage = image_r7c2
                    currentImageButton.setImageResource(android.R.color.transparent)
                    evaluatePair()
                    delay(isPair)
                    selectCount = 3
                }
                3 //runnable is not finished. May not select any other cards
                -> {
                }
            }
        }
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

    fun doCardAction(location: Int){

        when(selectCount){

            1 -> {
                currentSelection(location)
                pairX = location
                soundCardFirst.start()
                selectCount = 2
            }
            2 -> {
                previousSelection(location)
                pairY = location
                evaluatePair()
                delay(isPair)
                selectCount = 3
            }
            3 -> {//nothing
            }
        }

    }

    fun currentSelection(location:Int){

        when (location){

            0 -> {
                currentImage = image_r0c0
                currentImageButton = imagebutton_r0c0
            }
            1 -> {
                currentImage = image_r0c1
                currentImageButton = imagebutton_r0c1
            }
            2 -> {
                currentImage = image_r0c2
                currentImageButton = imagebutton_r0c2
            }
            3 -> {
                currentImage = image_r0c3
                currentImageButton = imagebutton_r0c3
            }
            4 -> {
                currentImage = image_r0c4
                currentImageButton = imagebutton_r0c4
            }
            5 -> {
                currentImage = image_r0c5
                currentImageButton = imagebutton_r0c5
            }
            6 -> {
                currentImage = image_r0c6
                currentImageButton = imagebutton_r0c6
            }
            7 -> {
                currentImage = image_r1c0
                currentImageButton = imagebutton_r1c0
            }
            8 -> {
                currentImage = image_r1c1
                currentImageButton = imagebutton_r1c1
            }
            9 -> {
                currentImage = image_r1c2
                currentImageButton = imagebutton_r1c2
            }
            10 -> {
                currentImage = image_r1c3
                currentImageButton = imagebutton_r1c3
            }
            11 -> {
                currentImage = image_r1c4
                currentImageButton = imagebutton_r1c4
            }
            12 -> {
                currentImage = image_r1c5
                currentImageButton = imagebutton_r1c5
            }
            13 -> {
                currentImage = image_r1c6
                currentImageButton = imagebutton_r1c6
            }
            14 -> {
                currentImage = image_r2c0
                currentImageButton = imagebutton_r2c0
            }
            15 -> {
                currentImage = image_r2c1
                currentImageButton = imagebutton_r2c1
            }
            16 -> {
                currentImage = image_r2c2
                currentImageButton = imagebutton_r2c2
            }
            17 -> {
                currentImage = image_r2c3
                currentImageButton = imagebutton_r2c3
            }
            18 -> {
                currentImage = image_r2c4
                currentImageButton = imagebutton_r2c4
            }
            19 -> {
                currentImage = image_r2c5
                currentImageButton = imagebutton_r2c5
            }
            20 -> {
                currentImage = image_r2c6
                currentImageButton = imagebutton_r2c6
            }
            21 -> {
                currentImage = image_r3c0
                currentImageButton = imagebutton_r3c0
            }
            22 -> {
                currentImage = image_r3c1
                currentImageButton = imagebutton_r3c1
            }
            23 -> {
                currentImage = image_r3c2
                currentImageButton = imagebutton_r3c2
            }
            24 -> {
                currentImage = image_r3c3
                currentImageButton = imagebutton_r3c3
            }
            25 -> {
                currentImage = image_r3c4
                currentImageButton = imagebutton_r3c4
            }
            26 -> {
                currentImage = image_r3c5
                currentImageButton = imagebutton_r3c5
            }
            27 -> {
                currentImage = image_r3c6
                currentImageButton = imagebutton_r3c6
            }
            28 -> {
                currentImage = image_r4c0
                currentImageButton = imagebutton_r4c0
            }
            29 -> {
                currentImage = image_r4c1
                currentImageButton = imagebutton_r4c1
            }
            30 -> {
                currentImage = image_r4c2
                currentImageButton = imagebutton_r4c2
            }
            31 -> {
                currentImage = image_r4c3
                currentImageButton = imagebutton_r4c3
            }
            32 -> {
                currentImage = image_r4c4
                currentImageButton = imagebutton_r4c4
            }
            33 -> {
                currentImage = image_r4c5
                currentImageButton = imagebutton_r4c5
            }
            34 -> {
                currentImage = image_r4c6
                currentImageButton = imagebutton_r4c6
            }
            35 -> {
                currentImage = image_r5c0
                currentImageButton = imagebutton_r5c0
            }
            36 -> {
                currentImage = image_r5c1
                currentImageButton = imagebutton_r5c1
            }
            37 -> {
                currentImage = image_r5c2
                currentImageButton = imagebutton_r5c2
            }
            38 -> {
                currentImage = image_r5c3
                currentImageButton = imagebutton_r5c3
            }
            39 -> {
                currentImage = image_r5c4
                currentImageButton = imagebutton_r5c4
            }
            40 -> {
                currentImage = image_r5c5
                currentImageButton = imagebutton_r5c5
            }
            41 -> {
                currentImage = image_r5c6
                currentImageButton = imagebutton_r5c6
            }
            42 -> {
                currentImage = image_r6c0
                currentImageButton = imagebutton_r6c0
            }
            43 -> {
                currentImage = image_r6c1
                currentImageButton = imagebutton_r6c1
            }
            44 -> {
                currentImage = image_r6c2
                currentImageButton = imagebutton_r6c2
            }
            45 -> {
                currentImage = image_r6c3
                currentImageButton = imagebutton_r6c3
            }
            46 -> {
                currentImage = image_r6c4
                currentImageButton = imagebutton_r6c4
            }
            47 -> {
                currentImage = image_r6c5
                currentImageButton = imagebutton_r6c5
            }
            48 -> {
                currentImage = image_r6c6
                currentImageButton = imagebutton_r6c6
            }
            49 -> {
                currentImage = image_r7c0
                currentImageButton = imagebutton_r7c0
            }
            50 -> {
                currentImage = image_r7c1
                currentImageButton = imagebutton_r7c1
            }
            51 -> {
                currentImage = image_r7c2
                currentImageButton = imagebutton_r7c2
            }
        }
        currentImageButton.setImageResource(android.R.color.transparent)
    }

    fun previousSelection(location:Int){

        when (location){

            0 -> {
                previousImage = image_r0c0
                previousImageButton = imagebutton_r0c0
            }
            1 -> {
                previousImage = image_r0c1
                previousImageButton = imagebutton_r0c1
            }
            2 -> {
                previousImage = image_r0c2
                previousImageButton = imagebutton_r0c2
            }
            3 -> {
                previousImage = image_r0c3
                previousImageButton = imagebutton_r0c3
            }
            4 -> {
                previousImage = image_r0c4
                previousImageButton = imagebutton_r0c4
            }
            5 -> {
                previousImage = image_r0c5
                previousImageButton = imagebutton_r0c5
            }
            6 -> {
                previousImage = image_r0c6
                previousImageButton = imagebutton_r0c6
            }
            7 -> {
                previousImage = image_r1c0
                previousImageButton = imagebutton_r1c0
            }
            8 -> {
                previousImage = image_r1c1
                previousImageButton = imagebutton_r1c1
            }
            9 -> {
                previousImage = image_r1c2
                previousImageButton = imagebutton_r1c2
            }
            10 -> {
                previousImage = image_r1c3
                previousImageButton = imagebutton_r1c3
            }
            11 -> {
                previousImage = image_r1c4
                previousImageButton = imagebutton_r1c4
            }
            12 -> {
                previousImage = image_r1c5
                previousImageButton = imagebutton_r1c5
            }
            13 -> {
                previousImage = image_r1c6
                previousImageButton = imagebutton_r1c6
            }
            14 -> {
                previousImage = image_r2c0
                previousImageButton = imagebutton_r2c0
            }
            15 -> {
                previousImage = image_r2c1
                previousImageButton = imagebutton_r2c1
            }
            16 -> {
                previousImage = image_r2c2
                previousImageButton = imagebutton_r2c2
            }
            17 -> {
                previousImage = image_r2c3
                previousImageButton = imagebutton_r2c3
            }
            18 -> {
                previousImage = image_r2c4
                previousImageButton = imagebutton_r2c4
            }
            19 -> {
                previousImage = image_r2c5
                previousImageButton = imagebutton_r2c5
            }
            20 -> {
                previousImage = image_r2c6
                previousImageButton = imagebutton_r2c6
            }
            21 -> {
                previousImage = image_r3c0
                previousImageButton = imagebutton_r3c0
            }
            22 -> {
                previousImage = image_r3c1
                previousImageButton = imagebutton_r3c1
            }
            23 -> {
                previousImage = image_r3c2
                previousImageButton = imagebutton_r3c2
            }
            24 -> {
                previousImage = image_r3c3
                previousImageButton = imagebutton_r3c3
            }
            25 -> {
                previousImage = image_r3c4
                previousImageButton = imagebutton_r3c4
            }
            26 -> {
                previousImage = image_r3c5
                previousImageButton = imagebutton_r3c5
            }
            27 -> {
                previousImage = image_r3c6
                previousImageButton = imagebutton_r3c6
            }
            28 -> {
                previousImage = image_r4c0
                previousImageButton = imagebutton_r4c0
            }
            29 -> {
                previousImage = image_r4c1
                previousImageButton = imagebutton_r4c1
            }
            30 -> {
                previousImage = image_r4c2
                previousImageButton = imagebutton_r4c2
            }
            31 -> {
                previousImage = image_r4c3
                previousImageButton = imagebutton_r4c3
            }
            32 -> {
                previousImage = image_r4c4
                previousImageButton = imagebutton_r4c4
            }
            33 -> {
                previousImage = image_r4c5
                previousImageButton = imagebutton_r4c5
            }
            34 -> {
                previousImage = image_r4c6
                previousImageButton = imagebutton_r4c6
            }
            35 -> {
                previousImage = image_r5c0
                previousImageButton = imagebutton_r5c0
            }
            36 -> {
                previousImage = image_r5c1
                previousImageButton = imagebutton_r5c1
            }
            37 -> {
                previousImage = image_r5c2
                previousImageButton = imagebutton_r5c2
            }
            38 -> {
                previousImage = image_r5c3
                previousImageButton = imagebutton_r5c3
            }
            39 -> {
                previousImage = image_r5c4
                previousImageButton = imagebutton_r5c4
            }
            40 -> {
                previousImage = image_r5c5
                previousImageButton = imagebutton_r5c5
            }
            41 -> {
                previousImage = image_r5c6
                previousImageButton = imagebutton_r5c6
            }
            42 -> {
                previousImage = image_r6c0
                previousImageButton = imagebutton_r6c0
            }
            43 -> {
                previousImage = image_r6c1
                previousImageButton = imagebutton_r6c1
            }
            44 -> {
                previousImage = image_r6c2
                previousImageButton = imagebutton_r6c2
            }
            45 -> {
                previousImage = image_r6c3
                previousImageButton = imagebutton_r6c3
            }
            46 -> {
                previousImage = image_r6c4
                previousImageButton = imagebutton_r6c4
            }
            47 -> {
                previousImage = image_r6c5
                previousImageButton = imagebutton_r6c5
            }
            48 -> {
                previousImage = image_r6c6
                previousImageButton = imagebutton_r6c6
            }
            49 -> {
                previousImage = image_r7c0
                previousImageButton = imagebutton_r7c0
            }
            50 -> {
                previousImage = image_r7c1
                previousImageButton = imagebutton_r7c1
            }
            51 -> {
                previousImage = image_r7c2
                previousImageButton = imagebutton_r7c2
            }
        }
        previousImageButton.setImageResource(android.R.color.transparent)
    }

    private fun delay(pair: Int) {
        val handler = Handler()
        when (pair) {
            0 //Not Pair
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
                        intent.putExtra("EXTRA_attemptCount", attemptCount)
                        startActivity(intent)
                        finish()

                    }
                }, 1500)
                Toast.makeText(this, "Pair", Toast.LENGTH_SHORT).show()
            }
//            else -> Log.d("MainActivity.delay()", "delay not work")
        }
    }

    private fun evaluatePair() {
        Log.d("MainActivity() evaluatePair() ", " order[$pairX] order[$pairY] ")

        if (pairX == pairY) { //FIX error case: self pair
            Toast.makeText(this, "IMPOSSIBLE Self Pair", Toast.LENGTH_SHORT).show()
            isPair = 0
        } else if (deckOrder[pairX] == deckOrder[pairY]) {
            isPair = 1 //IS PAIR
            soundCardSecondPair.start()
        } else {
            isPair = 0 //NOT PAIR
            soundCardSecondNot.start()
        }
    }

}