package me.threebecause.learning.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AreaEntity(val countryCode: String? = null,
                      val ensignUrl: String,
                      val id: Int,
                      val name: String) : Parcelable
