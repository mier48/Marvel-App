package com.albertomier.marveldemo.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.marveldemo.databinding.FragmentHomeBinding
import com.albertomier.marveldemo.domain.model.Hero
import com.albertomier.marveldemo.ui.adapter.HeroAdapter
import com.albertomier.marveldemo.ui.viewmodel.HeroesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val heroesViewModel: HeroesViewModel by viewModels()

    lateinit var adapter: HeroAdapter

    private var limit = 10
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val manager = GridLayoutManager(activity, 2)
        binding.heroesList.layoutManager = manager

        heroesViewModel.onCreate(limit)

        heroesViewModel.heroesModel.observe(viewLifecycleOwner, Observer {
            isLoading = false
            binding.load.visibility = View.GONE

            if (binding.heroesList.adapter != null) {
                (binding.heroesList.adapter as HeroAdapter).updateReceiptsList(it)
            } else {
                adapter = HeroAdapter(
                    it,
                    { hero -> onHeroSelected(hero) },
                    { hero -> addToFavorite(hero) })
                binding.heroesList.adapter = adapter
            }
        })

        heroesViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            //binding.progress.isVisible = it
        })

        binding.heroesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = manager.childCount
                val pastVisibleItem = manager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if (!isLoading) {
                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        limit += 10

                        isLoading = true
                        binding.load.visibility = View.VISIBLE

                        heroesViewModel.onCreate(limit)
                    }

                }

                super.onScrolled(recyclerView, dx, dy)
            }
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
            heroesViewModel.deleteFavorite(hero, limit)
        } else {
            heroesViewModel.addToFavorite(hero, limit)
        }
    }
}