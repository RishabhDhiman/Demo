package com.example.startwarblaster.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.startwarblaster.R
import com.example.startwarblaster.adapter.PlayersAdapter
import com.example.startwarblaster.dataclass.PlayerAndPointsData
import com.example.startwarblaster.utils.Callback
import com.example.startwarblaster.utils.INTENT_MATCHES_DETAILS
import com.example.startwarblaster.viewmodel.PointsTableViewModel
import com.example.startwarblaster.viewmodel.Status

class PointsTableActivity : AppCompatActivity(), Callback<PlayerAndPointsData> {
    private lateinit var recyclerView: RecyclerView
    private val pointsTableViewModel: PointsTableViewModel by viewModels<PointsTableViewModel>()

    /**TODO Many things have been left due to time constraint, that filer of score can be implemented using inheritence and it will become more dynamic.
    Due to little time I was not able to do that.**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_points_table)
        pointsTableViewModel.matchDataLiveData.observe(this) {
            if (it.status == Status.LOADING) return@observe
            recyclerView.adapter = it.data?.let { list -> PlayersAdapter(list, this) }
        }
        pointsTableViewModel.loadPointsTable(resources)
        recyclerView = findViewById(R.id.rvPointsTable)
    }

    override fun onCall(playerAndPointsData: PlayerAndPointsData) {
        startActivity(
            Intent(this, MatchesDetailActivity::class.java).putExtra(
                INTENT_MATCHES_DETAILS,
                /***Will use serializable or some other approach but short on time.**/
                playerAndPointsData.id
            )
        )
    }
}