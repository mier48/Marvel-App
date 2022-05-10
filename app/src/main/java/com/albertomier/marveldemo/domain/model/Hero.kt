package com.albertomier.marveldemo.domain.model

import com.albertomier.marveldemo.data.database.entities.HeroEntity
import com.albertomier.marveldemo.data.model.HeroModel

data class Hero(
    val id: Int,
    val name: String,
    val imagePath: String,
    val description: String,
    var fav: Boolean = false
)

fun HeroModel.toDomain() =
    Hero(
        id = id,
        name = name,
        imagePath = thumbnail.path.replace(
            "http://",
            "https://"
        ) + "/portrait_xlarge." + thumbnail.extension,
        description = description
    )

fun HeroEntity.toDomain() =
    Hero(id = id, name = name, imagePath = image, description = description, fav = fav)