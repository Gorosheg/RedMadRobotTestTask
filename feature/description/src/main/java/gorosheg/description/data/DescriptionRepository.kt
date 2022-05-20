package gorosheg.description.data

import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Location
import io.reactivex.Single

internal interface DescriptionRepository {

    fun getDescription(): Single<Description>

    fun getLocation(): Single<Location>
}
