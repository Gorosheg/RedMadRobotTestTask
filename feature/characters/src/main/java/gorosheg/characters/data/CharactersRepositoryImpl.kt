package gorosheg.characters.data

import gorosheg.myapplication.model.Character
import gorosheg.network.NetworkDatasource
import io.reactivex.Single

internal class CharactersRepositoryImpl(
    private val network: NetworkDatasource
) : CharactersRepository {

    override fun getCharacters(page: Int): Single<List<Character>> {
        return network.getCharacters(page)
    }
}