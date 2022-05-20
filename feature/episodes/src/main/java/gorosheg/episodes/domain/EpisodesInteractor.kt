package gorosheg.episodes.domain

import gorosheg.myapplication.model.Episode
import io.reactivex.Single

interface EpisodesInteractor {

    fun getEpisodes(): Single<List<Episode>>
}