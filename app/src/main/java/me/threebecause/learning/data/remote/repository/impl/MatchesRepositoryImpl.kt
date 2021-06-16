package me.threebecause.learning.data.remote.repository.impl

import me.threebecause.learning.data.model.CompetitionEntity
import me.threebecause.learning.data.model.MatchEntity
import me.threebecause.learning.data.model.Matche
import me.threebecause.learning.data.remote.api.ApiService
import me.threebecause.learning.data.remote.repository.MatchesRepository
import javax.inject.Inject
import javax.inject.Named

class MatchesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MatchesRepository {
    override suspend fun getCount(): Int {
        return 0
    }

    override suspend fun getMatches(
        teamId: Int,
        dateFrom: String,
        dateTo: String
    ): List<Matche> = apiService.getMatchesForTeam(teamId, dateFrom, dateTo).matches
}