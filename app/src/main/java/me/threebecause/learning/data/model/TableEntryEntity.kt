package me.threebecause.learning.data.model

import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.parcelize.Parcelize

@Parcelize
data class TableEntryEntity(
    val position: Int? = null,
    val team: TeamEntity,
    val playedGames: Int? = null,
    val won: Int? = null,
    val draw: Int? = null,
    val lost: Int? = null,
    val points: Int? = null,
    val goalsFor: Int? = null,
    val goalsAgainst: Int? = null,
    val goalDifference: Int? = null,
) : Parcelable