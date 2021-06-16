package me.threebecause.learning.data.remote.repository

import me.threebecause.learning.data.model.CompetitionEntity
import me.threebecause.learning.data.model.MatchEntity
import me.threebecause.learning.data.model.Matche

interface MatchesRepository {
    suspend fun getCount(): Int?
    suspend fun getMatches(teamId: Int, dateFrom: String, dateTo: String): List<Matche>?
}