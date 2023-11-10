package com.example.mypokedex.domain

import com.example.mypokedex.data.entities.Pokemon
import com.example.mypokedex.data.entities.PokemonResponse
import javax.inject.Inject

class PokemonUseCase
@Inject constructor(
    private val repository: PokemonRepository
) {
    suspend fun getPokemonList(): List<Pokemon> {
        return repository.getPokemonList()
    }

    suspend fun getPokemonListV2(): PokemonResponse {
        return repository.getPokemonListFromApi()
    }

}