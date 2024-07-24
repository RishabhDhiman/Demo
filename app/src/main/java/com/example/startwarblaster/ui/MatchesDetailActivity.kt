package com.example.startwarblaster.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.startwarblaster.R
import com.example.startwarblaster.adapter.MatchDetailsAdapter
import com.example.startwarblaster.utils.INTENT_MATCHES_DETAILS
import com.example.startwarblaster.viewmodel.MatchDetailViewModel
import com.example.startwarblaster.viewmodel.Status


class MatchesDetailActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val matchDetailsViewModel: MatchDetailViewModel by viewModels<MatchDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_points_table)
        recyclerView = findViewById(R.id.rvPointsTable)
        val playerId = intent.getIntExtra(INTENT_MATCHES_DETAILS, -1)
        matchDetailsViewModel.matchDataLiveData.observe(this) {
            if (it.status == Status.LOADING) return@observe
            matchDetailsViewModel
            recyclerView.adapter = it.data?.let { list -> MatchDetailsAdapter(list) }
        }
        matchDetailsViewModel.loadMatchDetails(resources, playerId)
    }
}