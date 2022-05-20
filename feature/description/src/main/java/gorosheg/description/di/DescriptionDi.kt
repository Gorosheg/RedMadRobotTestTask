package gorosheg.description.di

import gorosheg.description.data.DescriptionRepository
import gorosheg.description.data.DescriptionRepositoryImpl
import gorosheg.description.presentation.DescriptionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val descriptionModule = module {
    viewModel { params ->
        DescriptionViewModel(get { params })
    }

    factory<DescriptionRepository> { params ->
        DescriptionRepositoryImpl(get(), params.get())
    }
}
