package gorosheg.myapplication.model

import java.io.Serializable

data class Episode(
    val name: String,
    val airDate: String,
) : Serializable, Item<String> {

    override val id: String = name
}