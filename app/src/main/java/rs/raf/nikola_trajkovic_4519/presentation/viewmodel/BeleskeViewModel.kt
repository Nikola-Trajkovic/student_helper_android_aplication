package rs.raf.nikola_trajkovic_4519.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.nikola_trajkovic_4519.data.models.Beleske
import rs.raf.nikola_trajkovic_4519.data.repositories.BeleskeRepository
import rs.raf.nikola_trajkovic_4519.presentation.contract.MainContract
import rs.raf.nikola_trajkovic_4519.presentation.view.states.AddBeleskeState
import rs.raf.nikola_trajkovic_4519.presentation.view.states.BeleskeState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BeleskeViewModel(
    private val beleskeRepository: BeleskeRepository,
) : ViewModel(), MainContract.ViewModelBeleske {

    private val subscriptions = CompositeDisposable()
    override val beleskeState: MutableLiveData<BeleskeState> = MutableLiveData()
    override val addDone: MutableLiveData<AddBeleskeState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                beleskeRepository
                    .getBeleskeFilter(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    beleskeState.value = BeleskeState.Success(it)
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }


    override fun addBeleske(naslov: String, content: String) {
        val subscription = beleskeRepository
            .insert(naslov,content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddBeleskeState.Success
                },
                {
                    addDone.value = AddBeleskeState.Error("Error happened while adding movie")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllBeleske() {
        val subscription = beleskeRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beleskeState.value = BeleskeState.Success(it)
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getBeleskeFilter(name: String) {
        publishSubject.onNext(name)
    }

    override fun update(id: Long, naslov: String, content: String) {
        val subscription = beleskeRepository
            .update(id,naslov,content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beleskeState.value = BeleskeState.Update
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun archive(id: Long) {
        val subscription = beleskeRepository
            .archive(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beleskeState.value = BeleskeState.Update
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun delete(id: Long) {
        val subscription = beleskeRepository
            .delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beleskeState.value = BeleskeState.Delete
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getArchived() {
        val subscription = beleskeRepository
            .getArchived()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beleskeState.value = BeleskeState.Success(it)
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }


}