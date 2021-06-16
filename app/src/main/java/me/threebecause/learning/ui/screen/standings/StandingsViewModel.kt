package me.threebecause.learning.ui.screen.standings

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.threebecause.learning.data.model.StagesEntity
import me.threebecause.learning.data.remote.repository.StandingsRepository
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(
    private val standingsRepository: StandingsRepository
) : ViewModel() {
    val standingList  = MutableLiveData<List<StagesEntity>>()

    fun getStandingData(id : Int){
        try {
            viewModelScope.launch {
                standingList.value = standingsRepository.getCompetition(id = id) ?: listOf()
            }
        }catch (error: Throwable){
            Log.d("TAG213", "error:  ${error.message}")
        }
    }
}