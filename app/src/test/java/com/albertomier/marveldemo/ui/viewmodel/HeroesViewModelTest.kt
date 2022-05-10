package com.albertomier.marveldemo.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.albertomier.marveldemo.domain.AddHeroToFavorite
import com.albertomier.marveldemo.domain.GetHeroes
import com.albertomier.marveldemo.domain.GetHeroesFromDatabase
import com.albertomier.marveldemo.domain.RemoveHeroFromFavorite
import com.albertomier.marveldemo.domain.model.Hero
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HeroesViewModelTest {

    @RelaxedMockK
    private lateinit var getHeroes: GetHeroes

    @RelaxedMockK
    private lateinit var addHeroToFavorite: AddHeroToFavorite

    @RelaxedMockK
    private lateinit var removeHeroFromFavorite: RemoveHeroFromFavorite

    @RelaxedMockK
    private lateinit var getHeroesFromDatabase: GetHeroesFromDatabase

    private lateinit var heroesViewModel: HeroesViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        heroesViewModel = HeroesViewModel(
            getHeroes,
            addHeroToFavorite,
            removeHeroFromFavorite,
            getHeroesFromDatabase
        )

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all heroes`() = runTest {
        //Given
        val hero1 = Hero(1, "Héroe 1", "", "description", false)
        val hero2 = Hero(2, "Héroe 2", "", "description", false)
        val myList = listOf(hero1, hero2)
        coEvery { getHeroes(2) } returns myList

        //When
        heroesViewModel.onCreate(2)

        //Then
        assert(heroesViewModel.heroesModel.value == myList)
    }
}