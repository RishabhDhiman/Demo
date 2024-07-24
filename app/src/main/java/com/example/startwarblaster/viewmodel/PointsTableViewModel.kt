package com.example.startwarblaster.viewmodel

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startwarblaster.dataclass.PlayerAndPointsData
import com.example.startwarblaster.repository.MatchRepository
import kotlinx.coroutines.launch

class PointsTableViewModel : ViewModel() {
    val matchDataLiveData = MutableLiveData<State<List<PlayerAndPointsData>>>()

    fun loadPointsTable(resources: Resources) {
        matchDataLiveData.value = State.loading()
        viewModelScope.launch {
            MatchRepository.loadPointsTable(resources)
            matchDataLiveData.postValue(State.success(MatchRepository.getPlayerList()))
        }
    }
}