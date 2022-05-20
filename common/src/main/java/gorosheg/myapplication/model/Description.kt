package gorosheg.myapplication.model

class Description(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String
) {

    companion object {

        fun empty() = Description(0, "", "", "", "")
    }
}