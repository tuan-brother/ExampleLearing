package me.threebecause.learning.data.model

data class CompetitionLeague(
    val competitions: List<CompetitionX>,
    val count: Int,
    val filters: FiltersX
)