package me.threebecause.learning.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScoreEntity(
    val winner: String? = null,
    val fullTime: HashMap<String, Int>? = null,
    val halfTime: HashMap<String, Int>? = null,
    val extraTime: HashMap<String, Int>? = null,
    val penalties: HashMap<String, Int>? = null,
) : Parcelable {
    companion object {
        const val HOME_TEAM_WINNER = "HOME_TEAM"
        const val AWAY_TEAM_WINNER = "HOME_TEAM"
        const val DRAW = "HOME_TEAM"
    }

    fun getHomeTeamScore(): String {
        return if (fullTime?.get("homeTeam") != null) "" + fullTime["homeTeam"] else "-"
    }

    fun getAwayTeamScore(): String {
        return if (fullTime?.get("awayTeam") != null) "" + fullTime["awayTeam"] else "-"
    }
}
