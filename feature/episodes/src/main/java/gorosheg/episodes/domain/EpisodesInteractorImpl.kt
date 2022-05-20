package gorosheg.episodes.domain

import gorosheg.episodes.data.EpisodesRepository
import gorosheg.myapplication.model.Episode
import io.reactivex.Single

internal class EpisodesInteractorImpl(private val repository: EpisodesRepository) : EpisodesInteractor {

    override fun getEpisodes(): Single<List<Episode>> {
        return repository.getEpisodes()
    }
}