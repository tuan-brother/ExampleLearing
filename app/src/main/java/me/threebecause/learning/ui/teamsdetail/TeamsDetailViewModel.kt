package me.threebecause.learning.ui.teamsdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.threebecause.learning.data.model.TeamEntity
import me.threebecause.learning.data.remote.api.ApiService
import me.threebecause.learning.data.remote.repository.TeamRepository
import javax.inject.Inject

@HiltViewModel
class TeamsDetailViewModel @Inject constructor(
    private val teamRepository: TeamRepository
) : ViewModel() {
    val team = MutableLiveData<TeamEntity>()
    fun getTeamById(id: Int) {
        try {
            viewModelScope.launch {
                team.value = teamRepository.getTeamById(id)
            }
        }catch (error : Throwable){}
    }
}