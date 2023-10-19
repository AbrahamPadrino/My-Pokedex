package com.example.mypokedex

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {

    //1.0 pasar datos del fragment al activity

    interface PokemonSelectectListener {
        fun onPokemonSelected(pokemon: Pokemon)
    }
    private lateinit var pokemonSelectListener: PokemonSelectectListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pokemonSelectListener = try {
            context as PokemonSelectectListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                "$context must implement PokemonSelectectListener"
            )
        }
    }
    //

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_list, container, false)
        val recycler: RecyclerView = view.findViewById<RecyclerView>(R.id.pokemon_recycler)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        //agregando adapter
        val adapter = PokemonAdapter()
        recycler.adapter = adapter
        //1.1 pasa el pokemon seleccionado al activity
        adapter.onItemClickListener = {
            pokemonSelectListener.onPokemonSelected(it)
        }
        //
        val pokemonList: MutableList<Pokemon> = mutableListOf(
            Pokemon(1, "Bulbasaur", 45, 49, 49, 45, Pokemon.PokemonType.GRASS,"https://seeklogo.com/images/B/bulbasaur-logo-8A27924C02-seeklogo.com.png", R.raw.bulbasaur),
            Pokemon(2, "Ivysaur", 60, 62, 63, 60, Pokemon.PokemonType.GRASS, "https://static.wikia.nocookie.net/pokepediatotal/images/8/89/Ivysaur_dream_world.png/revision/latest?cb=20130121144454&path-prefix=es", R.raw.unknown1),
            Pokemon(3, "Venusaur", 80, 82, 83, 80, Pokemon.PokemonType.GRASS, "https://images.gameinfo.io/pokemon/256/p3f436.png", R.raw.venusaur1),
            Pokemon(4, "Charmander", 39, 52, 43, 65, Pokemon.PokemonType.FIRE, "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokemon-blue-version/d/d4/Charmander.gif", R.raw.charmander),
            Pokemon(5, "Charmeleon", 58, 64, 58, 80, Pokemon.PokemonType.FIRE, "https://static.wikia.nocookie.net/minipokedex/images/3/34/5-Charmeleon.png/revision/latest?cb=20170929215817&path-prefix=es",R.raw.charmeleon),
            Pokemon(6, "Charizard", 78, 84, 78, 100, Pokemon.PokemonType.FIRE, "https://img.pokemondb.net/sprites/scarlet-violet/normal/charizard.png", R.raw.unknown2),
            Pokemon(7, "Squirtle", 44, 48, 65, 43, Pokemon.PokemonType.WATER, "https://seeklogo.com/images/S/squirtle-logo-4EA3D6011D-seeklogo.com.png", R.raw.squirtle),
            Pokemon(8, "Wartortle", 59, 63, 80, 58, Pokemon.PokemonType.WATER, "https://guidesarchive.ign.com/guides/12045/images/wartortle.gif", R.raw.wartortle),
            Pokemon(9, "Blastoise", 79, 83, 100, 78, Pokemon.PokemonType.WATER, "https://images.gameinfo.io/pokemon/256/p9f111s1.png", R.raw.blastoise),
            Pokemon(10, "Caterpie", 45, 30, 35, 45, Pokemon.PokemonType.FIGHTER, "https://www.serebii.net/pokemonsleep/pokemon/shiny/10.png", R.raw.caterpie),
            Pokemon(11, "Metapod", 50, 20, 55, 30, Pokemon.PokemonType.FIGHTER, "https://static.wikia.nocookie.net/pokemon-encyclopedia/images/c/cd/011Metapod.png/revision/latest/scale-to-width-down/250?cb=20171129172909", R.raw.metapod),
            Pokemon(12, "Butterfree", 60, 45, 50, 70, Pokemon.PokemonType.FIGHTER, "https://static.wikia.nocookie.net/espokemon/images/9/99/Sableye.png/revision/latest/thumbnail/width/360/height/360?cb=20141113214721", R.raw.unknown3),
            Pokemon(13, "Weedle", 40, 35, 30, 50, Pokemon.PokemonType.GRASS, "https://static.wikia.nocookie.net/espokemon/images/9/9f/Umbreon.png/revision/latest/thumbnail/width/360/height/360?cb=20140208131643", R.raw.unknown4),
            Pokemon(14, "Kakuna", 45, 25, 50, 35, Pokemon.PokemonType.GRASS, "https://archives.bulbagarden.net/media/upload/thumb/6/6d/0134Vaporeon.png/250px-0134Vaporeon.png", R.raw.unknown5),
            Pokemon(15, "Beedrill", 65, 90, 40, 75, Pokemon.PokemonType.GRASS, "https://upload.wikimedia.org/wikipedia/en/4/43/Pok%C3%A9mon_Mewtwo_art.png", R.raw.unknown6),
            Pokemon(16, "Pidgey", 40, 45, 40, 40, Pokemon.PokemonType.FIGHTER, "https://urpgstatic.com/images/pokemon-home.png", R.raw.unknown8),
            Pokemon(17, "Pidgeotto", 63, 60, 55, 63, Pokemon.PokemonType.FIGHTER, "https://static.wikia.nocookie.net/espokemon/images/9/95/Emolga.png/revision/latest/thumbnail/width/360/height/360?cb=20150321124404", R.raw.unknown7),
            Pokemon(18, "Pidgeot", 83, 80, 75, 83, Pokemon.PokemonType.FIGHTER, "https://images.gameinfo.io/pokemon/256/p145f332.png", R.raw.unknown9),
        )
        adapter.submitList(pokemonList)

        return view
    }


}