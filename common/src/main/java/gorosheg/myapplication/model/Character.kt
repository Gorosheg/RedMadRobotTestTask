package gorosheg.myapplication.model

import java.io.Serializable

data class Character(
    override val id: Int,
    val name: String,
    val image: String
) : Serializable, Item<Int>