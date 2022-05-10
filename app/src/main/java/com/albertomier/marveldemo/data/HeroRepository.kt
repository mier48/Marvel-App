package com.albertomier.marveldemo.data

import com.albertomier.marveldemo.data.database.dao.HeroDao
import com.albertomier.marveldemo.data.database.entities.HeroEntity
import com.albertomier.marveldemo.data.model.HeroModel
import com.albertomier.marveldemo.data.network.HeroService
import com.albertomier.marveldemo.domain.model.Hero
import com.albertomier.marveldemo.domain.model.toDomain
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val api: HeroService,
    private val heroDao: HeroDao
) {
    suspend fun getHeroesFromApi(limit: Int): List<Hero> {
        val response: List<HeroModel> = api.getHeroes(limit)

        return response.map { it.toDomain() }
    }

    suspend fun getHeroById(id: Int): Hero {
        val response: List<HeroModel> = api.getHeroById(id)

        return if (!response.isNullOrEmpty()) {
            response[0].toDomain()
        } else {
            heroDao.getHeroById(id).toDomain()
        }
    }

    suspend fun getHeroByIdFromDatabse(id: Int): Hero {
        return heroDao.getHeroById(id).toDomain()
    }

    suspend fun getHeroesByNameFromApi(name: String): List<Hero> {
        val response: List<HeroModel> = api.getHeroesByName(name)

        return response.map { it.toDomain() }
    }

    suspend fun getHeroesByNameFromDatabase(name: String): List<Hero> {
        val response: List<HeroEntity> = heroDao.getHeroesByName(name)

        return response.map { it.toDomain() }
    }

    suspend fun getHeroesFromDatabase(limit: Int): List<Hero> {
        val response: List<HeroEntity> = heroDao.getHeroes(limit)

        return response.map { it.toDomain() }
    }

    suspend fun getFavoritesHeroes(): List<Hero> {
        val response: List<HeroEntity> = heroDao.getFavoritesHeroes()

        return if (response.isNullOrEmpty()) {
            emptyList()
        } else {
            response.map { it.toDomain() }
        }
    }

    suspend fun addHeroToFavorite(id: Int) {
        heroDao.addHeroToFavorite(id)
    }

    suspend fun removeHeroFromFavorite(id: Int) {
        heroDao.removeHeroFromFavorite(id)
    }

    suspend fun insertHeroes(heroes: List<HeroEntity>) {
        heroDao.insertAll(heroes)
    }

    suspend fun clearHeroes() {
        heroDao.deleteAllHeroes()
    }
}