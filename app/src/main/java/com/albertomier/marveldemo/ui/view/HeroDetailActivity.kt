package com.albertomier.marveldemo.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import com.albertomier.marveldemo.R
import com.albertomier.marveldemo.databinding.ActivityHeroDetailBinding
import com.albertomier.marveldemo.domain.model.Hero
import com.albertomier.marveldemo.ui.viewmodel.HeroViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroDetailBinding

    private val heroViewModel: HeroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id: Int = intent.getIntExtra("_id", 0)

        heroViewModel.onCreate(id)

        heroViewModel.heroModel.observe(this, Observer { hero ->
            supportActionBar?.title = hero.name
            binding.heroName.text = hero.name
            binding.heroDescription.text = hero.description

            val placeholder =
                ResourcesCompat.getDrawable(resources, R.mipmap.placeholder_image, null)
            Picasso.get().load(hero.imagePath).placeholder(placeholder!!)
                .centerCrop().fit().into(binding.heroImage)

            if (hero.fav) {
                binding.addToFavorite.setImageDrawable(
                    AppCompatResources.getDrawable(
                        this,
                        R.drawable.ic_star
                    )
                )
            } else {
                binding.addToFavorite.setImageDrawable(
                    AppCompatResources.getDrawable(
                        this,
                        R.drawable.ic_empty_star
                    )
                )
            }

            binding.addToFavorite.setOnClickListener {
                addToFavorite(hero)
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun addToFavorite(hero: Hero) {
        if (hero.fav) {
            heroViewModel.deleteFavorite(hero)
        } else {
            heroViewModel.addToFavorite(hero)
        }
    }
}