package gorosheg.characters.presentation

import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.HttpException
import gorosheg.characters.domain.CharactersInteractor
import gorosheg.myapplication.model.Character
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

internal class CharactersViewModel(private val interactor: CharactersInteractor) : ViewModel() {

    private val _error = PublishSubject.create<Throwable>()
    val error: Observable<Throwable> = _error

    fun loadCharacters(): Single<List<Character>> {
        return interactor.loadCharacters()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(_error::onNext)
            .onErrorReturn { emptyList() }
    }

}