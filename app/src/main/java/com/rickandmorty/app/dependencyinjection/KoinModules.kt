package com.rickandmorty.app.dependencyinjection

import com.rickandmorty.app.data.characters.datasources.CharactersRemoteDataSource
import com.rickandmorty.app.data.characters.datasources.ICharactersRemoteDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {

}

val dataModule = module {
    factoryOf(::CharactersRemoteDataSource) bind ICharactersRemoteDataSource::class
}

val domainModule = module {

}
