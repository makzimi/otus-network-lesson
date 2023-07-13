package ru.otus.network.start.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.otus.network.databinding.ItemGalleryBinding
import ru.otus.network.start.domain.RaMCharacter

class CharactersAdapter : ListAdapter<RaMCharacter, CharactersViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            ItemGalleryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val entity = getItem(position)
        entity?.let {
            holder.bind(entity)
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<RaMCharacter>() {

    override fun areItemsTheSame(oldItem: RaMCharacter, newItem: RaMCharacter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RaMCharacter, newItem: RaMCharacter): Boolean {
        return oldItem == newItem
    }
}
