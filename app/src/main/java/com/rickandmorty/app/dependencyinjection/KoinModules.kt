package com.rickandmorty.app.dependencyinjection

import com.rickandmorty.app.data.characters.datasources.CharactersRemoteDataSource
import com.rickandmorty.app.data.characters.datasources.ICharactersRemoteDataSource
import com.rickandmorty.app.data.characters.repository.CharactersRepository
import com.rickandmorty.app.domain.characters.repository.ICharactersRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {

}

val dataModule = module {
    factoryOf(::CharactersRemoteDataSource) bind ICharactersRemoteDataSource::class
    factoryOf(::CharactersRepository) bind ICharactersRepository::class
}

val domainModule = module {
}
