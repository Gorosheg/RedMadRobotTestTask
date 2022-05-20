package gorosheg.characters.domain

import gorosheg.characters.data.CharactersRepository
import gorosheg.myapplication.model.Character
import io.reactivex.Single

internal class CharactersInteractorImpl(private val repository: CharactersRepository) : CharactersInteractor {

    override fun getCharacters(page: Int): Single<List<Character>> {
        return repository.getCharacters(page)
    }
}