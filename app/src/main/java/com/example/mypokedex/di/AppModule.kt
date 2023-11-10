package com.example.mypokedex.di

import com.example.mypokedex.data.PokemonRepositoryImpl
import com.example.mypokedex.data.remote.PokemonService
import com.example.mypokedex.domain.PokemonRepository
import com.example.mypokedex.domain.PokemonUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesPokemonRepository(pokemonService: PokemonService): PokemonRepository {
        return PokemonRepositoryImpl(pokemonService)
    }

    @Singleton
    @Provides
    fun providesPokemonUseCase(pokemonRepository: PokemonRepository): PokemonUseCase {
        return PokemonUseCase(pokemonRepository)
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesPokemonApiService(retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)

}