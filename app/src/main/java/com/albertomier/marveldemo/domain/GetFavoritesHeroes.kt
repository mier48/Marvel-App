package com.albertomier.marveldemo.domain

import com.albertomier.marveldemo.data.HeroRepository
import com.albertomier.marveldemo.domain.model.Hero
import javax.inject.Inject

class GetFavoritesHeroes @Inject constructor(private val repository: HeroRepository) {

    suspend operator fun invoke(): List<Hero> {
        return repository.getFavoritesHeroes()
    }
}