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

class GetHeroByIdTest {
    @RelaxedMockK
    private lateinit var repository: HeroRepository

    lateinit var getHeroById: GetHeroById

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getHeroById = GetHeroById(repository)
    }

    @Test
    fun `when data from api is not empty then return one hero`() = runBlocking{
        //Given
        val hero1 = Hero(1, "Héroe 1", "", "description", false)
        val myList = listOf(hero1)
        coEvery { repository.getHeroById(1) } returns myList

        //When
        val response = getHeroById(1)

        //Then
        assert(response == myList.first())
    }
}