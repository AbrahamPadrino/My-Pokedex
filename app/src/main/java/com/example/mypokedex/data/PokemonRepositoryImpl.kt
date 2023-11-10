package com.example.mypokedex.data

import com.example.mypokedex.data.entities.Pokemon
import com.example.mypokedex.data.entities.PokemonResponse
import com.example.mypokedex.data.remote.BaseDataSource
import com.example.mypokedex.data.remote.PokemonService
import com.example.mypokedex.domain.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl
@Inject constructor(
    private val pokemonService: PokemonService
) : BaseDataSource(), PokemonRepository {
    override suspend fun getPokemonList(): List<Pokemon> {
        return PokemonDataSource.pokemonList
    }

    override suspend fun getPokemonListFromApi(): PokemonResponse {
        return getResult {
            pokemonService.getPokemonList()

        }

    }

}