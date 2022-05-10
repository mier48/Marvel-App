package com.albertomier.marveldemo.domain

import com.albertomier.marveldemo.data.HeroRepository
import com.albertomier.marveldemo.domain.model.Hero
import javax.inject.Inject

class GetHeroByIdFromDatabase @Inject constructor(private val repository: HeroRepository) {

    suspend operator fun invoke(id: Int): Hero {
        return repository.getHeroByIdFromDatabse(id)
    }
}