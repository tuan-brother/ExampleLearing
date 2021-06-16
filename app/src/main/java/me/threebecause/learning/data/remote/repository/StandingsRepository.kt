package me.threebecause.learning.data.remote.repository

import me.threebecause.learning.data.model.CompetitionEntity
import me.threebecause.learning.data.model.StagesEntity

interface StandingsRepository {
    suspend fun getStages(id: Int): CompetitionEntity
    suspend fun getCompetition(id : Int): List<StagesEntity>?
}