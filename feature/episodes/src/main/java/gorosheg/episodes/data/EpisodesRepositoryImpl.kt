package gorosheg.episodes.data

import gorosheg.myapplication.model.Episode
import gorosheg.network.NetworkDatasource
import io.reactivex.Single

internal class EpisodesRepositoryImpl(
    private val network: NetworkDatasource,
    private val CharacterId: Int
) : EpisodesRepository {

    override fun getEpisodes(): Single<List<Episode>> {
        return network.getEpisodes(CharacterId)
    }
}