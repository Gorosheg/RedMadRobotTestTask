package gorosheg.network.model

import com.google.gson.annotations.SerializedName

internal class EpisodesResponse(
    @SerializedName("results")
    val episodes: List<EpisodeResponse>
)

internal class EpisodeResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("characters")
    val characters: List<String>,
)