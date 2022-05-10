package com.albertomier.marveldemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.marveldemo.R
import com.albertomier.marveldemo.domain.model.Hero

class HeroAdapter(
    private var heroesList: List<Hero>,
    private val onClickListener: (Hero) -> Unit,
    private val addToFavoriteListener: (Hero) -> Unit
) : RecyclerView.Adapter<HeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeroViewHolder(layoutInflater.inflate(R.layout.hero, parent, false))
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val item = heroesList[position]
        holder.render(item, onClickListener, addToFavoriteListener)
    }

    override fun getItemCount(): Int {
        if (!heroesList.isNullOrEmpty()) {
            return heroesList.size
        }
        return 0
    }

    fun updateReceiptsList(newlist: List<Hero>) {
        heroesList = emptyList()
        heroesList = newlist
        notifyDataSetChanged()
    }
}