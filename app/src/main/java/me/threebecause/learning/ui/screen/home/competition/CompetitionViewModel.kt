package me.threebecause.learning.ui.screen.home.competition

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.threebecause.learning.data.model.CompetitionX
import me.threebecause.learning.data.remote.repository.CompetitionRepository
import javax.inject.Inject

@HiltViewModel
class CompetitionViewModel @Inject constructor(
    private val competitionRepository: CompetitionRepository
) : ViewModel() {
    val competitions = MutableLiveData<List<CompetitionX>>()
    init {
        getCompetitions()
    }
    fun getCompetitions() {
        viewModelScope.launch {
            try {
                val competitionList = ArrayList(competitionRepository.getCompetitions() ?: listOf())
                competitions.value = competitionList
            } catch (ex: Throwable) {
                Log.d("TAG234", "Error:   ${ex.message}")
            }
        }
    }
}