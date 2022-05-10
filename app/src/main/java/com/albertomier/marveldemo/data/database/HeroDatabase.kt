package com.albertomier.marveldemo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.albertomier.marveldemo.data.database.dao.HeroDao
import com.albertomier.marveldemo.data.database.entities.HeroEntity

@Database(entities = [HeroEntity::class], version = 1)
abstract class HeroDatabase : RoomDatabase() {
    abstract fun getHeroDao(): HeroDao
}