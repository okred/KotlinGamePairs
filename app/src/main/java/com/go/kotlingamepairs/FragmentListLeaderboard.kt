package com.go.kotlingamepairs

import android.app.ListFragment
import android.os.Bundle

class FragmentListLeaderboard : ListFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val whatever = CustomListAdapter(activity, LeaderboardData.nameArray,
                LeaderboardData.attemptsArray, LeaderboardData.timeArray)
        //TODO - get data from SQL array table instead
        //TODO - organise table into correct order
        //TODO - assign colors
        //textViewRatingValueListView.setTextColor(Color.parseColor("#FF0000"))
        listAdapter = whatever
    }

}