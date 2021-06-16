package me.threebecause.learning.data.remote.response

import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import me.threebecause.learning.data.model.*

@Keep
@Parcelize
data class StandingsResponse(
    val season: CurrentSeasonX? = null,
    val standings: List<StagesEntity>? = null
) : BaseResponse() {
}