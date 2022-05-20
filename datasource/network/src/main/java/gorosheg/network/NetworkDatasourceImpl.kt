package gorosheg.network

import gorosheg.myapplication.model.Character
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Episode
import gorosheg.myapplication.model.Location
import gorosheg.network.model.*
import io.reactivex.Single

internal class NetworkDatasourceImpl(private val api: CharactersApi) : NetworkDatasource {

    override fun getCharacters(page: Int): Single<List<Character>> {
        return api.getAll(page).map { it.toSimpleCharacter() }
    }

    override fun getDescription(id: Int): Single<Description> {
        return api.getCharacter(id).map { it.toSimpleDescription() }
    }

    override fun getLocation(id: Int): Single<Location> {
        return api.getLocation(id).map { it.toSimpleLocation() }
    }

    override fun getEpisodes(id: Int): Single<List<Episode>> {
        return api.getEpisodes().map { it.toSimpleEpisodes(id) }
    }
}