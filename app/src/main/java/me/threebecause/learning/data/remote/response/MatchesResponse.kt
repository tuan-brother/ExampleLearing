package me.threebecause.learning.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import me.threebecause.learning.data.model.CompetitionEntity
import me.threebecause.learning.data.model.MatchEntity

@Parcelize
data class MatchesResponse(
    val count: Int? = null,
    val matches: List<MatchEntity>? = null,
) : Parcelable
