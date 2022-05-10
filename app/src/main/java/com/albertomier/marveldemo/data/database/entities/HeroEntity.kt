package com.albertomier.marveldemo.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albertomier.marveldemo.domain.model.Hero

@Entity(tableName = "heroes_table")
data class HeroEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "fav") var fav: Boolean
)

fun Hero.toDatabase() = HeroEntity(id = id, name = name, image = imagePath, description = description, fav = fav)