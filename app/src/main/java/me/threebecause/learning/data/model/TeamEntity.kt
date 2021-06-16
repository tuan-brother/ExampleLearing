package me.threebecause.learning.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamEntity(
    var id: Int,
    val name: String? = null,
    val crestUrl: String? = null,
    val clubColors: String? = null,
    val venue: String? = null,
    val founded: Int? = null,
    val area: AreaX? = null,
    val website: String? = null,
    val tla: String? = null
) : Parcelable
