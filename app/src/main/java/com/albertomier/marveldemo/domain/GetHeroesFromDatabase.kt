package com.albertomier.marveldemo.domain

import com.albertomier.marveldemo.data.HeroRepository
import com.albertomier.marveldemo.data.database.entities.toDatabase
import com.albertomier.marveldemo.domain.model.Hero
import javax.inject.Inject

class GetHeroesFromDatabase @Inject constructor(private val repository: HeroRepository) {

    suspend operator fun invoke(limit: Int): List<Hero> {
        return repository.getHeroesFromDatabase(limit)
    }
}