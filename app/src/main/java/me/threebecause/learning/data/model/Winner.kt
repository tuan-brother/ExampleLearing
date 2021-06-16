package me.threebecause.learning.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Winner(
    val id: Int? = null,
    val name: String? = null,
    val shortName: String? = null,
    val tla: String? = null,
    val crestUrl: String? = null
) : Parcelable