package me.threebecause.learning.data.model

data class Matche(
    val awayTeam: AwayTeam,
    val competition: Competition,
    val group: String? = null,
    val homeTeam: HomeTeam,
    val id: Int,
    val lastUpdated: String? = null,
    val matchday: Int? = null,
    val odds: Odds,
    val referees: List<Referee>,
    val score: Score,
    val season: CurrentSeasonX,
    val stage: String? = null,
    val status: String? = null,
    val utcDate: String? = null
)