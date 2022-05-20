package gorosheg.episodes.di

import gorosheg.episodes.data.EpisodesRepository
import gorosheg.episodes.data.EpisodesRepositoryImpl
import gorosheg.episodes.domain.EpisodesInteractor
import gorosheg.episodes.domain.EpisodesInteractorImpl
import gorosheg.episodes.presentation.EpisodesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodeModule = module {
    viewModel { params ->
        EpisodesViewModel(get { params })
    }

    factory<EpisodesInteractor> { params ->
        EpisodesInteractorImpl(get { params })
    }

    factory<EpisodesRepository> { params ->
        EpisodesRepositoryImpl(get(), params.get())
    }
}