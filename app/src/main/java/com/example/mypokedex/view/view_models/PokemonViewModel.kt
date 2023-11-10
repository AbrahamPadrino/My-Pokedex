package com.example.mypokedex.view.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.data.entities.Pokemon
import com.example.mypokedex.data.entities.PokemonResponse
import com.example.mypokedex.domain.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel
    @Inject constructor(
        private val pokemonUseCase: PokemonUseCase
    ): ViewModel() {

    private val _liveData : MutableLiveData<List<Pokemon>> = MutableLiveData<List<Pokemon>>()
    val liveData : LiveData<List<Pokemon>> get() = _liveData

    private val _liveDataPokemonResponse : MutableLiveData<PokemonResponse> = MutableLiveData<PokemonResponse>()
    val liveDataPokemonResponse : LiveData<PokemonResponse> get() = _liveDataPokemonResponse


    fun getPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {

            _liveData.postValue(pokemonUseCase.getPokemonList())
        }
    }
    fun getPokemonListV2() {
        viewModelScope.launch(Dispatchers.IO) {

            _liveDataPokemonResponse.postValue(pokemonUseCase.getPokemonListV2())

        }
    }


}