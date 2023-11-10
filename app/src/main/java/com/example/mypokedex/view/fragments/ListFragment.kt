package com.example.mypokedex.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
import com.example.mypokedex.data.entities.Pokemon
import com.example.mypokedex.view.adapters.PokemonAdapter
import com.example.mypokedex.view.view_models.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    //1.0 pasar datos del fragment al activity

    private val pokemonViewModel: PokemonViewModel by viewModels()

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
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler: RecyclerView = view.findViewById(R.id.pokemon_recycler)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        //agregando adapter
        val adapter = PokemonAdapter()
        recycler.adapter = adapter
        //1.1 pasa el pokemon seleccionado al activity
        adapter.onItemClickListener = {
            pokemonSelectListener.onPokemonSelected(it)
        }
        pokemonViewModel.getPokemonListV2()

        pokemonViewModel.liveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        pokemonViewModel.liveDataPokemonResponse.observe(viewLifecycleOwner) {
            Toast.makeText(this.requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }


    }


}