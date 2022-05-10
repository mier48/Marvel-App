package com.albertomier.marveldemo.domain

import com.albertomier.marveldemo.data.HeroRepository
import com.albertomier.marveldemo.domain.model.Hero
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetHeroesTest {

    @RelaxedMockK
    private lateinit var repository: HeroRepository

    lateinit var getHeroes: GetHeroes

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getHeroes = GetHeroes(repository)
    }

    @Test
    fun `when the api doesnt return anything or return something then get values from database`() = runBlocking {
        //Given
        coEvery { repository.getHeroesFromApi(2) } returns emptyList()

        //When
        getHeroes(2)

        //Then
        coVerify(exactly = 1) { repository.getHeroesFromDatabase(2) }
    }
}