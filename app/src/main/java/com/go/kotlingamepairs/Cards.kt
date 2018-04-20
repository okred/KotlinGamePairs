package com.go.kotlingamepairs

import android.content.Context
import android.util.Log

import java.util.concurrent.ThreadLocalRandom

class Cards(private var ctx: Context) {

    internal var deckId = IntArray(52)
    internal var order = IntArray(52)

    internal var id = 0

    fun fillDeck() {

        //FILL deck 52 cards
        for (i in 0..51) {

            id = ctx.resources.getIdentifier("com.go.kotlingamepairs:drawable/ic_card" + Integer.toString(i), null, null)
            deckId[i] = id
        }

        //COUNT suit
        var j = 0
        //CREATE array order to validate pairs
        for (i in 0..51) {
            //ACE of(HEART/SPADE/CLUBS/DIAMONDS) = 0
            //TWO of (HEART/SPADE/CLUBS/DIAMONDS) = 1
            //...
            //KING of (HEART/SPADE/CLUBS/DIAMONDS) = 12
            if (j == 13) {
                j = 0
            }
            order[i] = j
            j++
        }

    }

    fun shuffleDeck() {

        //Fisher-Yates Shuffle
        val random = ThreadLocalRandom.current()
        for (i in order.indices) {
            val index = random.nextInt(i + 1)

            val a = deckId[index]
            deckId[index] = deckId[i]
            deckId[i] = a

            //COPY deckId[position] for validate pairs
            val b = order[index]
            order[index] = order[i]
            order[i] = b
        }
    }

}
