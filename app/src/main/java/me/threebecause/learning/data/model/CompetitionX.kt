package me.threebecause.learning.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompetitionX(
    val area: AreaX,
    val code: String,
    val currentSeason: CurrentSeasonX,
    val emblemUrl: String? = null,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val numberOfAvailableSeasons: Int,
    val plan: String
) : Parcelable