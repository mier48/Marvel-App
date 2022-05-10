package com.albertomier.marveldemo.domain

import com.albertomier.marveldemo.data.HeroRepository
import com.albertomier.marveldemo.domain.model.Hero
import javax.inject.Inject

class GetHeroById @Inject constructor(private val repository: HeroRepository) {

    suspend operator fun invoke(id: Int): Hero {
        val hero = repository.getHeroById(id)
        val heroDatabase = repository.getHeroByIdFromDatabse(id)

        if (heroDatabase.id == id) {
            hero.fav = heroDatabase.fav
        }

        return hero
    }
}