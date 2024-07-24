package com.example.startwarblaster.viewmodel

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startwarblaster.dataclass.MatchInfoData
import com.example.startwarblaster.repository.MatchRepository
import kotlinx.coroutines.launch

class MatchDetailViewModel : ViewModel() {
    val matchDataLiveData = MutableLiveData<State<ArrayList<MatchInfoData>>>()

    fun loadMatchDetails(resources: Resources, playerId: Int) {
        matchDataLiveData.value = State.loading()
        viewModelScope.launch {
            MatchRepository.loadPointsTable(resources)
            matchDataLiveData.postValue(State.success(MatchRepository.getMatchesByPlayerId(playerId)))
        }
    }
}