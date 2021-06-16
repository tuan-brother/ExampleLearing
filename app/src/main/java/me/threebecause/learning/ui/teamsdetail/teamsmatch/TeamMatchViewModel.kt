package me.threebecause.learning.ui.teamsdetail.teamsmatch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.threebecause.learning.data.model.MatchEntity
import me.threebecause.learning.data.model.Matche
import me.threebecause.learning.data.remote.repository.MatchesRepository
import javax.inject.Inject

@HiltViewModel
class TeamMatchViewModel @Inject constructor(
    val matchesRepository: MatchesRepository
) : ViewModel() {
    val matches = MutableLiveData<List<Matche>>()

    fun getMatches(id: Int, fromTime: String, toTime: String) {
        try {
            viewModelScope.launch {
                matches.value = matchesRepository.getMatches(id, fromTime, toTime)
            }
        }catch (error : Throwable){
            Log.d("TAG234", "${error.message}/TeamMatch")
        }
    }
}