package me.threebecause.learning.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AreaX(
    val countryCode: String? = null,
    val ensignUrl: String? = null,
    val id: Int,
    val name: String
) : Parcelable