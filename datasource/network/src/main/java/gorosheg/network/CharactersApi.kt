package gorosheg.network

import gorosheg.network.model.DescriptionResponse
import gorosheg.network.model.CharactersResponse
import gorosheg.network.model.EpisodesResponse
import gorosheg.network.model.LocationResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CharactersApi {

    @GET("character/?")
    fun getAll(@Query("page") page: Int): Single<CharactersResponse>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int): Single<DescriptionResponse>

    @GET("location/{id}")
    fun getLocation(@Path("id") id: Int): Single<LocationResponse>

    @GET("episode")
    fun getEpisodes(): Single<EpisodesResponse>
}