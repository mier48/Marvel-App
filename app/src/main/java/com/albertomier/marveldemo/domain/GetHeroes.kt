package com.albertomier.marveldemo.domain

import com.albertomier.marveldemo.data.HeroRepository
import com.albertomier.marveldemo.data.database.entities.toDatabase
import com.albertomier.marveldemo.domain.model.Hero
import javax.inject.Inject

class GetHeroes @Inject constructor(private val repository: HeroRepository) {

    suspend operator fun invoke(limit: Int): List<Hero> {
        val heroes = repository.getHeroesFromApi(limit)

        if (heroes.isNotEmpty()) {
            repository.insertHeroes(heroes.map { it.toDatabase() })
        }

        return repository.getHeroesFromDatabase(limit)
    }
}