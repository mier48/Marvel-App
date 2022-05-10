package com.albertomier.marveldemo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.marveldemo.domain.AddHeroToFavorite
import com.albertomier.marveldemo.domain.GetHeroById
import com.albertomier.marveldemo.domain.GetHeroes
import com.albertomier.marveldemo.domain.RemoveHeroFromFavorite
import com.albertomier.marveldemo.domain.model.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val getHeroById: GetHeroById,
    private val addHeroToFavorite: AddHeroToFavorite,
    private val removeHeroFromFavorite: RemoveHeroFromFavorite
) : ViewModel() {

    var heroModel = MutableLiveData<Hero>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(id: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getHeroById(id)

            heroModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun addToFavorite(hero: Hero) {
        viewModelScope.launch {
            addHeroToFavorite(hero.id)

            val result = getHeroById(hero.id)

            heroModel.postValue(result)
        }
    }

    fun deleteFavorite(hero: Hero) {
        viewModelScope.launch {
            removeHeroFromFavorite(hero.id)

            val result = getHeroById(hero.id)

            heroModel.postValue(result)
        }
    }
}