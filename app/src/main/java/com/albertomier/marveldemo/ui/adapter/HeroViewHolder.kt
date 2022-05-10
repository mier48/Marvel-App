package com.albertomier.marveldemo.ui.adapter

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.marveldemo.R
import com.albertomier.marveldemo.databinding.HeroBinding
import com.albertomier.marveldemo.domain.model.Hero
import com.squareup.picasso.Picasso

class HeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = HeroBinding.bind(view)

    fun render(hero: Hero, onClickListener: (Hero) -> Unit, addToFavoriteListener: (Hero) -> Unit) {
        val context = binding.heroImage.context

        binding.heroName.text = hero.name

        val placeholder =
            ResourcesCompat.getDrawable(context.resources, R.mipmap.placeholder_image, null)

        Picasso.get().load(hero.imagePath).placeholder(placeholder!!).centerCrop().fit()
            .into(binding.heroImage)

        itemView.setOnClickListener {
            onClickListener(hero)
        }

        if (hero.fav) {
            binding.addToFavorite.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_star
                )
            )
        } else {
            binding.addToFavorite.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_empty_star
                )
            )
        }

        binding.addToFavorite.setOnClickListener {
            addToFavoriteListener(hero)
        }
    }
}