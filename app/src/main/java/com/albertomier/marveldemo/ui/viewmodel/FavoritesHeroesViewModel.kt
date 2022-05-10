package com.albertomier.marveldemo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.marveldemo.domain.AddHeroToFavorite
import com.albertomier.marveldemo.domain.GetFavoritesHeroes
import com.albertomier.marveldemo.domain.RemoveHeroFromFavorite
import com.albertomier.marveldemo.domain.model.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesHeroesViewModel @Inject constructor(
    private val getFavoritesHeroes: GetFavoritesHeroes,
    private val addHeroToFavorite: AddHeroToFavorite,
    private val removeHeroFromFavorite: RemoveHeroFromFavorite
) : ViewModel() {

    var heroesModel = MutableLiveData<List<Hero>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getFavoritesHeroes()

            heroesModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun addToFavorite(hero: Hero) {
        viewModelScope.launch {
            isLoading.postValue(true)

            addHeroToFavorite(hero.id)

            val result = getFavoritesHeroes()

            if (!result.isNullOrEmpty()) {
                heroesModel.postValue(result)
            }

            isLoading.postValue(false)
        }
    }

    fun deleteFavorite(hero: Hero) {
        viewModelScope.launch {
            isLoading.postValue(true)

            removeHeroFromFavorite(hero.id)

            val result = getFavoritesHeroes()

            heroesModel.postValue(result)
            isLoading.postValue(false)
        }
    }
}