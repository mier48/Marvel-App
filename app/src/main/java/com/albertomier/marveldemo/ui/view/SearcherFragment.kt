package com.albertomier.marveldemo.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.albertomier.marveldemo.R
import com.albertomier.marveldemo.databinding.FragmentHomeBinding
import com.albertomier.marveldemo.databinding.FragmentSearcherBinding
import com.albertomier.marveldemo.domain.model.Hero
import com.albertomier.marveldemo.ui.adapter.HeroAdapter
import com.albertomier.marveldemo.ui.viewmodel.HeroesSearcherViewModel
import com.albertomier.marveldemo.ui.viewmodel.HeroesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearcherFragment : Fragment() {

    private var _binding: FragmentSearcherBinding? = null
    private val binding get() = _binding!!

    private val heroesSearcherViewModel: HeroesSearcherViewModel by viewModels()

    private var query: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearcherBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val manager = GridLayoutManager(activity, 2)
        binding.heroesList.layoutManager = manager


        heroesSearcherViewModel.heroesModel.observe(viewLifecycleOwner, Observer {
            binding.heroesList.adapter =
                HeroAdapter(it, { hero -> onHeroSelected(hero) }, { hero -> addToFavorite(hero) })
        })

        heroesSearcherViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })

        binding.searcherEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.isNotEmpty()) {
                    query = s.toString().lowercase()
                    heroesSearcherViewModel.byName(query!!)
                } else {
                    heroesSearcherViewModel.empty()
                }
            }
        })

        return root
    }

    override fun onResume() {
        super.onResume()
        if (query != null) {
            heroesSearcherViewModel.byName(query!!)
        }
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
            heroesSearcherViewModel.deleteFavorite(hero, query!!)
        } else {
            heroesSearcherViewModel.addToFavorite(hero, query!!)
        }
    }
}