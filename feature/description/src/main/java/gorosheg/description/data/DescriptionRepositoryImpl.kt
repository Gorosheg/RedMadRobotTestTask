package gorosheg.description.data

import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Location
import gorosheg.network.NetworkDatasource
import io.reactivex.Single

internal class DescriptionRepositoryImpl(
    private val network: NetworkDatasource,
    private val characterId: Int
) : DescriptionRepository {

    override fun getDescription(): Single<Description> {
        return network.getDescription(characterId)
    }

    override fun getLocation(): Single<Location> {
        return network.getLocation(characterId)
    }
}