package me.threebecause.learning.data.remote.repository.impl

import me.threebecause.learning.data.model.TeamEntity
import me.threebecause.learning.data.remote.api.ApiService
import me.threebecause.learning.data.remote.repository.TeamRepository
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    val apiService: ApiService
) : TeamRepository {
    override suspend fun getTeamById(id: Int): TeamEntity {
        return apiService.getTeamById(id)
    }
}