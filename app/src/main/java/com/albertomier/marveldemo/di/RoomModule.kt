package com.albertomier.marveldemo.di

import android.content.Context
import androidx.room.Room
import com.albertomier.marveldemo.data.database.HeroDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val HERO_DATABASE_NAME = "heroes_table"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, HeroDatabase::class.java, HERO_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideHeroDao(db: HeroDatabase) = db.getHeroDao()
}