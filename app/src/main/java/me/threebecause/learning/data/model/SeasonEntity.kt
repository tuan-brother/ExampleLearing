package me.threebecause.learning.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeasonEntity(val id: Int? = null, val currentMatchday: Int? = null) : Parcelable
