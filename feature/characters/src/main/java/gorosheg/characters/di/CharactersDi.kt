package gorosheg.characters.di

import gorosheg.characters.data.CharactersRepository
import gorosheg.characters.data.CharactersRepositoryImpl
import gorosheg.characters.domain.CharactersInteractor
import gorosheg.characters.domain.CharactersInteractorImpl
import gorosheg.characters.presentation.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    viewModel {
        CharactersViewModel(get())
    }

    factory<CharactersInteractor> {
        CharactersInteractorImpl(get())
    }

    factory<CharactersRepository> {
        CharactersRepositoryImpl(get())
    }
}