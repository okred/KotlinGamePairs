package com.go.kotlingamepairs

import android.content.Context
import android.util.Log

import java.util.concurrent.ThreadLocalRandom

class Cards(private var ctx: Context) {

    var deckIdArray = IntArray(52)
        internal set
    var orderArray = IntArray(52)
        internal set
    internal var id = 0

    fun fillDeck() {
        //Fill the deck with 52 cards
        for (i in 0..51) {

            id = ctx.resources.getIdentifier("com.go.kotlingamepairs:drawable/ic_card" + Integer.toString(i), null, null)
            deckIdArray[i] = id
            Log.d("DECKID", "DeckIDID: " + Integer.toString(id))
        }
        Log.d("DECKID", "DeckIDcontents: " + Integer.toString(deckIdArray[0]))

        var j = 0 //suit counter
        //CREATE array order to validate pairs
        for (i in 0..51) {
            //ACE of(HEART/SPADE/CLUBS/DIAMONDS) = 0
            //TWO of (HEART/SPADE/CLUBS/DIAMONDS) = 1
            //KING of (HEART/SPADE/CLUBS/DIAMONDS) = 12
            if (j == 13) {
                j = 0
            }
            orderArray[i] = j
            j++
            Log.d("Order: ", Integer.toString(orderArray[i]))
        }

    }

    fun shuffleDeck() {

        //Fisher-Yates Shuffle
        val random = ThreadLocalRandom.current()
        for (i in orderArray.indices) {
            val index = random.nextInt(i + 1)

            val a = deckIdArray[index]
            deckIdArray[index] = deckIdArray[i]
            deckIdArray[i] = a

            //COPY deckId position to validate pairs
            val b = orderArray[index]
            orderArray[index] = orderArray[i]
            orderArray[i] = b
        }
    }

}
