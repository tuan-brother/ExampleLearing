package me.threebecause.learning.data.remote.repository

import me.threebecause.learning.data.model.CompetitionX

interface CompetitionRepository {
    suspend fun getCount(): Int?
    suspend fun competitions(): List<CompetitionX>?
    suspend fun getCompetitions(): List<CompetitionX>?
}