package me.threebecause.learning.data.model

data class Score(
    val duration: String? = null,
    val extraTime: ExtraTime,
    val fullTime: FullTime,
    val halfTime: HalfTime,
    val penalties: Penalties,
    val winner: String? = null
)