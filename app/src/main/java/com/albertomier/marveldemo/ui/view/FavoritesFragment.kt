package com.albertomier.marveldemo.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.albertomier.marveldemo.databinding.FragmentFavoritesBinding
import com.albertomier.marveldemo.domain.model.Hero
import com.albertomier.marveldemo.ui.adapter.HeroAdapter
import com.albertomier.marveldemo.ui.viewmodel.FavoritesHeroesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesHeroesViewModel: FavoritesHeroesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val manager = GridLayoutManager(activity, 2)
        binding.heroesList.layoutManager = manager

        favoritesHeroesViewModel.onCreate()

        favoritesHeroesViewModel.heroesModel.observe(viewLifecycleOwner, Observer {
            binding.heroesList.adapter =
                HeroAdapter(it, { hero -> onHeroSelected(hero) }, { hero -> addToFavorite(hero) })
        })

        favoritesHeroesViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onHeroSelected(hero: Hero) {
        val intent = Intent(activity, HeroDetailActivity::class.java)
        intent.putExtra("_id", hero.id)

        startActivity(intent)
    }

    private fun addToFavorite(hero: Hero) {
        if (hero.fav) {
            favoritesHeroesViewModel.deleteFavorite(hero)
        } else {
            favoritesHeroesViewModel.addToFavorite(hero)
        }
    }
}