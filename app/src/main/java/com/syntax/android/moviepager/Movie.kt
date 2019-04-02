
package com.syntax.android.moviepager

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val rating: Double,
    @SerializedName("release_date") val releaseDate: String,
    val ranking: Int)