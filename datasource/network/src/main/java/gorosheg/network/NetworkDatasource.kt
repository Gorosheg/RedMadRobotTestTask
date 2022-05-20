package gorosheg.network

import gorosheg.myapplication.model.Character
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Episode
import gorosheg.myapplication.model.Location
import io.reactivex.Single

interface NetworkDatasource {

    fun getCharacters(page: Int): Single<List<Character>>

    fun getDescription(id: Int): Single<Description>

    fun getLocation(id: Int): Single<Location>

    fun getEpisodes(id: Int): Single<List<Episode>>
}