package com.go.kotlingamepairs

import android.content.Context

import java.util.concurrent.ThreadLocalRandom

class Cards(private var ctx: Context) {

    internal var deckId = IntArray(52)
    internal var deckOrder = IntArray(52)

    internal var id = 0

    fun fillDeck() {

        //FILL deck 52 cards
        for (i in 0..51) {

            id = ctx.resources.getIdentifier("com.go.kotlingamepairs:drawable/ic_card" + Integer.toString(i), null, null)
            deckId[i] = id
        }

        //COUNT suit
        var j = 0
        //CREATE array deckOrder to validate pairs
        for (i in 0..51) {
            //ACE of(HEART/SPADE/CLUBS/DIAMONDS) = 0
            //TWO of (HEART/SPADE/CLUBS/DIAMONDS) = 1
            //...
            //KING of (HEART/SPADE/CLUBS/DIAMONDS) = 12
            if (j == 13) {
                j = 0
            }
            deckOrder[i] = j
            j++
        }

    }

    fun shuffleDeck() {

        //Fisher-Yates Shuffle
        val random = ThreadLocalRandom.current()
        for (i in deckOrder.indices) {
            val index = random.nextInt(i + 1)

            val a = deckId[index]
            deckId[index] = deckId[i]
            deckId[i] = a

            //COPY deckId[position] for validate pairs
            val b = deckOrder[index]
            deckOrder[index] = deckOrder[i]
            deckOrder[i] = b
        }
    }

}
