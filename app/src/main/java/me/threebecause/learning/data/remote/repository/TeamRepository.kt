package me.threebecause.learning.data.remote.repository

import me.threebecause.learning.data.model.TeamEntity

interface TeamRepository {
    suspend fun getTeamById(id: Int): TeamEntity
}