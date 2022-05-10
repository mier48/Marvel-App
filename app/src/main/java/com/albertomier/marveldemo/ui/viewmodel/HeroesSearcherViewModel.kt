package com.albertomier.marveldemo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.marveldemo.domain.AddHeroToFavorite
import com.albertomier.marveldemo.domain.GetHeroesByName
import com.albertomier.marveldemo.domain.RemoveHeroFromFavorite
import com.albertomier.marveldemo.domain.model.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesSearcherViewModel @Inject constructor(
    private val getHeroesByName: GetHeroesByName,
    private val addHeroToFavorite: AddHeroToFavorite,
    private val removeHeroFromFavorite: RemoveHeroFromFavorite
) : ViewModel() {

    var heroesModel = MutableLiveData<List<Hero>>()
    val isLoading = MutableLiveData<Boolean>()

    fun empty() {
        viewModelScope.launch {
            isLoading.postValue(true)

            heroesModel.postValue(emptyList())
            isLoading.postValue(false)
        }
    }

    fun byName(name: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getHeroesByName(name)

            if (!result.isNullOrEmpty()) {
                heroesModel.postValue(result)
            }

            isLoading.postValue(false)
        }
    }

    fun addToFavorite(hero: Hero, name: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            addHeroToFavorite(hero.id)

            val result = getHeroesByName(name)

            if (!result.isNullOrEmpty()) {
                heroesModel.postValue(result)
            }

            isLoading.postValue(false)
        }
    }

    fun deleteFavorite(hero: Hero, name: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            removeHeroFromFavorite(hero.id)

            val result = getHeroesByName(name)

            if (!result.isNullOrEmpty()) {
                heroesModel.postValue(result)
            }

            isLoading.postValue(false)
        }
    }
}