package com.yanz.projectkuliah

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PartialRestaurant(
    @ColumnInfo(name = "r_id")
    val id: Int,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
)
