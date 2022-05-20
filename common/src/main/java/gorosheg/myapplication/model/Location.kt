package gorosheg.myapplication.model

class Location(
    val name: String,
    val dimension: String
) {

    companion object {

        fun empty() = Location("", "")
    }
}