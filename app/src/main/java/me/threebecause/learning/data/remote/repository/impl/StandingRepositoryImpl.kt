package me.threebecause.learning.data.remote.repository.impl

import me.threebecause.learning.data.model.CompetitionEntity
import me.threebecause.learning.data.model.StagesEntity
import me.threebecause.learning.data.remote.api.ApiService
import me.threebecause.learning.data.remote.repository.StandingsRepository
import javax.inject.Inject
import javax.inject.Named

class StandingRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : StandingsRepository {
    override suspend fun getStages(id: Int): CompetitionEntity = apiService.getCompetitionById(id)

    override suspend fun getCompetition(id: Int): List<StagesEntity>? =
        apiService.getCompetitionStandings(id).standings

}