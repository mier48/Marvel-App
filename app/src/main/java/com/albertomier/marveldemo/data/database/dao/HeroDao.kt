package com.albertomier.marveldemo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.albertomier.marveldemo.data.database.entities.HeroEntity

@Dao
interface HeroDao {

    @Query("SELECT * FROM heroes_table ORDER BY name ASC LIMIT 0, :limit")
    suspend fun getHeroes(limit: Int): List<HeroEntity>

    @Query("SELECT * FROM heroes_table WHERE fav = 1 ORDER BY name ASC")
    suspend fun getFavoritesHeroes(): List<HeroEntity>

    @Query("SELECT * FROM heroes_table WHERE id = :heroId")
    suspend fun getHeroById(heroId: Int): HeroEntity

    @Query("SELECT * FROM heroes_table WHERE name LIKE :heroName")
    suspend fun getHeroesByName(heroName: String): List<HeroEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hero: HeroEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(heroes: List<HeroEntity>)

    @Query("DELETE FROM heroes_table")
    suspend fun deleteAllHeroes()

    @Query("UPDATE heroes_table SET fav = 1 WHERE id = :heroId")
    suspend fun addHeroToFavorite(heroId: Int)

    @Query("UPDATE heroes_table SET fav = 0 WHERE id = :heroId")
    suspend fun removeHeroFromFavorite(heroId: Int)
}