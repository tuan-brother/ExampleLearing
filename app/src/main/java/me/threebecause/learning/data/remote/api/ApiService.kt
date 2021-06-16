package me.threebecause.learning.data.remote.api

import me.threebecause.learning.data.model.CompetitionEntity
import me.threebecause.learning.data.model.CompetitionLeague
import me.threebecause.learning.data.model.MatchesRespones
import me.threebecause.learning.data.model.TeamEntity
import me.threebecause.learning.data.remote.response.MatchResponse
import me.threebecause.learning.data.remote.response.MatchesResponse
import me.threebecause.learning.data.remote.response.StandingsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("competitions?plan=TIER_ONE")
    suspend fun getCompetitions(): CompetitionLeague

    @GET("${ApiPath.TOURNAMENT_ID}{id}")
    suspend fun getCompetitionById(@Path("id") id: Int): CompetitionEntity

    @GET("competitions/{id}/standings")
    suspend fun getCompetitionStandings(@Path("id") id: Int): StandingsResponse

    @GET("${ApiPath.TEAMS}{id}")
    suspend fun getTeamById(@Path("id") id: Int): TeamEntity

    @GET("${ApiPath.MATCHES}{id}")
    suspend fun getMatchById(@Path("id") id: Int): MatchResponse

    @GET("competitions/{id}/matches")
    suspend fun getMatchesForCompetition(
        @Path("id") id: Int,
        @Query("matchday") matchday: Int
    ): MatchResponse

    @GET("teams/{id}/matches")
    suspend fun getMatchesForTeam(
        @Path("id") teamId: Int,
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String
    ): MatchesRespones
}