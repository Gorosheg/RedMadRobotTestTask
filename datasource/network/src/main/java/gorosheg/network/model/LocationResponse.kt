package gorosheg.network.model

import com.google.gson.annotations.SerializedName

internal class LocationResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("dimension")
    val dimension: String
)
