package me.threebecause.learning.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentSeasonX(
    val currentMatchday: Int? = null,
    val endDate: String,
    val id: Int,
    val startDate: String,
    val winner: Winner? = null
) : Parcelable