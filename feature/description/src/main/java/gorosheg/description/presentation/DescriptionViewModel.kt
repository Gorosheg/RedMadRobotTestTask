package gorosheg.description.presentation

import androidx.lifecycle.ViewModel
import gorosheg.description.data.DescriptionRepository
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Location
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

internal class DescriptionViewModel(private val repository: DescriptionRepository) : ViewModel() {

    private var disposable = CompositeDisposable()

    private val _description = BehaviorSubject.create<Description>()
    val description: Observable<Description> = _description

    private val _location = BehaviorSubject.create<Location>()
    val location: Observable<Location> = _location

    private val _error = PublishSubject.create<Throwable>()
    val error: Observable<Throwable> = _error

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun getDescription() {
        disposable += repository.getDescription()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { _description.onNext(it) }
            .doOnError(_error::onNext)
            .onErrorReturn { Description.empty() }
            .subscribe()
    }

    fun getLocation() {
        disposable += repository.getLocation()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { _location.onNext(it) }
            .doOnError(_error::onNext)
            .onErrorReturn { Location.empty() }
            .subscribe()
    }
}
