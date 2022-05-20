package gorosheg.myapplication.model

interface Item<Type> {

    val id: Type

    override fun equals(other: Any?): Boolean
}