package gorosheg.episodes.presentation

import androidx.lifecycle.ViewModel
import gorosheg.episodes.domain.EpisodesInteractor
import gorosheg.myapplication.model.Character
import gorosheg.myapplication.model.Episode
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class EpisodesViewModel(private val interactor: EpisodesInteractor) : ViewModel() {

    private var disposable = CompositeDisposable()
    private val _success = BehaviorSubject.create<List<Episode>>()
    val success: Observable<List<Episode>> = _success

    private val _error = PublishSubject.create<Throwable>()
    val error: Observable<Throwable> = _error

    fun getEpisodes() {
        disposable += interactor.getEpisodes()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { _success.onNext(it) }
            .doOnError(_error::onNext)
            .onErrorReturn { emptyList() }
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}