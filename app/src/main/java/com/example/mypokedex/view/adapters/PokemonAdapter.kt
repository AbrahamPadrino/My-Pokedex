package com.example.mypokedex.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
import com.example.mypokedex.data.entities.Pokemon

class PokemonAdapter: ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(DiffCallback) {

    //permite al adapter conocer modificaciones en los items.
    companion object DiffCallback: DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }

    lateinit var onItemClickListener: (Pokemon) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon: Pokemon = getItem(position)
        holder.bind(pokemon)
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val pokemonId: TextView = view.findViewById(R.id.pokemon_id)
        private val pokemonName: TextView = view.findViewById(R.id.pokemon_name)
        private val typeImage: ImageView = view.findViewById(R.id.pokemon_type_image)

        fun bind(pokemon: Pokemon) {
            pokemonId.text = pokemon.id.toString()
            pokemonName.text = pokemon.name

            val imageId = when (pokemon.type) {
                Pokemon.PokemonType.GRASS -> R.drawable.grass_icon
                Pokemon.PokemonType.FIRE -> R.drawable.fire_icon
                Pokemon.PokemonType.WATER -> R.drawable.water_icon
                Pokemon.PokemonType.ELECTRIC -> R.drawable.electric_icon
                Pokemon.PokemonType.FIGHTER -> R.drawable.fighter_icon
            }
            typeImage.setImageResource(imageId)
            view.setOnClickListener {
                if (::onItemClickListener.isInitialized)
                    onItemClickListener(pokemon)
            }
        }

    }
}