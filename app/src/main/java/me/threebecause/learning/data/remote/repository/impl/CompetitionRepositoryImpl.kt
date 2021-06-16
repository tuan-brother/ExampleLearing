package me.threebecause.learning.data.remote.repository.impl

import android.util.Log
import me.threebecause.learning.data.model.CompetitionX
import me.threebecause.learning.data.remote.api.ApiService
import me.threebecause.learning.data.remote.repository.CompetitionRepository
import javax.inject.Inject

class CompetitionRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CompetitionRepository {
    override suspend fun getCount(): Int = apiService.getCompetitions().count

    override suspend fun competitions(): List<CompetitionX> {
        return listOf()
    }


    override suspend fun getCompetitions(): List<CompetitionX> {
        Log.d("TAG256", "competitions:  lay data" + apiService.getCompetitions().count)
        return apiService.getCompetitions().competitions
    }
}