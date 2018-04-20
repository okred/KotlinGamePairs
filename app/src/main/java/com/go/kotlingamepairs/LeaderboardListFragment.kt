package com.go.kotlingamepairs

import android.app.ListFragment
import android.os.Bundle

class LeaderboardListFragment : ListFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listAdapter = CustomListAdapter(activity, LeaderboardData.name,
                LeaderboardData.attempts)
        //TODO - get data from SQL array table instead
    }

}