package com.albertomier.marveldemo.domain

import com.albertomier.marveldemo.data.HeroRepository
import com.albertomier.marveldemo.domain.model.Hero
import javax.inject.Inject

class GetHeroById @Inject constructor(private val repository: HeroRepository) {

    suspend operator fun invoke(id: Int): Hero {
        val heroResponse = repository.getHeroById(id)

        return if (!heroResponse.isNullOrEmpty()) {
            val hero = heroResponse[0]
            val heroDatabase = repository.getHeroByIdFromDatabse(id)

            if (heroDatabase.id == id) {
                hero.fav = heroDatabase.fav
            }
            hero
        } else {
            repository.getHeroByIdFromDatabse(id)
        }
    }
}