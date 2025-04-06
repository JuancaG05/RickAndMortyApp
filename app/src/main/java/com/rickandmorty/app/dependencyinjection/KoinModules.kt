package com.rickandmorty.app.dependencyinjection

import com.rickandmorty.app.data.RickAndMortyDatabase
import com.rickandmorty.app.data.characters.datasources.CharactersLocalDataSource
import com.rickandmorty.app.data.characters.datasources.CharactersRemoteDataSource
import com.rickandmorty.app.data.characters.datasources.ICharactersLocalDataSource
import com.rickandmorty.app.data.characters.datasources.ICharactersRemoteDataSource
import com.rickandmorty.app.data.characters.repository.CharactersRepository
import com.rickandmorty.app.domain.characters.repository.ICharactersRepository
import com.rickandmorty.app.domain.characters.usecases.DeleteFavouriteCharacterUseCase
import com.rickandmorty.app.domain.characters.usecases.GetCharactersUseCase
import com.rickandmorty.app.domain.characters.usecases.GetFavouriteCharactersUseCase
import com.rickandmorty.app.domain.characters.usecases.UpsertFavouriteCharacterUseCase
import com.rickandmorty.app.presentation.characters.CharactersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::CharactersViewModel)
}

val dataModule = module {
    single { RickAndMortyDatabase.getDatabase(androidContext()) }
    single { get<RickAndMortyDatabase>().charactersDao() }
    factoryOf(::CharactersLocalDataSource) bind ICharactersLocalDataSource::class
    factoryOf(::CharactersRemoteDataSource) bind ICharactersRemoteDataSource::class
    factoryOf(::CharactersRepository) bind ICharactersRepository::class
}

val domainModule = module {
    factoryOf(::GetCharactersUseCase)
    factoryOf(::UpsertFavouriteCharacterUseCase)
    factoryOf(::DeleteFavouriteCharacterUseCase)
    factoryOf(::GetFavouriteCharactersUseCase)
}
