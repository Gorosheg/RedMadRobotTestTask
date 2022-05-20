package gorosheg.characters.domain

import gorosheg.myapplication.model.Character
import io.reactivex.Single

internal interface CharactersInteractor {

    fun getCharacters(page: Int): Single<List<Character>>
}