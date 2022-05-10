package com.albertomier.marveldemo.domain

import com.albertomier.marveldemo.data.HeroRepository
import com.albertomier.marveldemo.data.database.entities.toDatabase
import com.albertomier.marveldemo.domain.model.Hero
import javax.inject.Inject

class GetHeroesByName @Inject constructor(private val repository: HeroRepository) {

    suspend operator fun invoke(name: String): List<Hero> {
        val heroes = repository.getHeroesByNameFromApi(name)

        if (heroes.isNotEmpty()) {
            repository.insertHeroes(heroes.map { it.toDatabase() })
        }

        return repository.getHeroesByNameFromDatabase("%${name}%")
    }
}