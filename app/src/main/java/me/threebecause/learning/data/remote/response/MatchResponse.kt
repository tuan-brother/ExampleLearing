package me.threebecause.learning.data.remote.response

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import me.threebecause.learning.data.model.MatchEntity

@Keep
@Parcelize
data class MatchResponse(val match : MatchEntity? = null) : Parcelable