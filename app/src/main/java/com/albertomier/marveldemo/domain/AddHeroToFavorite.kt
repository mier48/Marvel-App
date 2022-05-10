package com.albertomier.marveldemo.domain

import com.albertomier.marveldemo.data.HeroRepository
import javax.inject.Inject

class AddHeroToFavorite @Inject constructor(private val repository: HeroRepository) {

    suspend operator fun invoke(id: Int) {
        repository.addHeroToFavorite(id)
    }
}