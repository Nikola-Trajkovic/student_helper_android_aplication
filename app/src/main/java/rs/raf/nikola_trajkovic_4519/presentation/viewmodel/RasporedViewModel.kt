package rs.raf.nikola_trajkovic_4519.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

import rs.raf.nikola_trajkovic_4519.data.models.Raspored// obrisati
import rs.raf.nikola_trajkovic_4519.data.models.Resource

import rs.raf.nikola_trajkovic_4519.data.repositories.RasporedRepository
import rs.raf.nikola_trajkovic_4519.presentation.contract.MainContract
import rs.raf.nikola_trajkovic_4519.presentation.view.states.RasporedState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class RasporedViewModel(
    private val rasporedRepository: RasporedRepository,
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val rasporedState: MutableLiveData<RasporedState> = MutableLiveData()


    override fun fetchAllRaspored() {
        val subscription = rasporedRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> rasporedState.value = RasporedState.Loading
                        is Resource.Success -> rasporedState.value = RasporedState.DataFetched
                        is Resource.Error -> rasporedState.value = RasporedState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    rasporedState.value = RasporedState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllRaspored() {
        val subscription = rasporedRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    rasporedState.value = RasporedState.Success(it)
                },
                {
                    rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun filter(grupe: String, dan: String, search: String) {

        var prvi: String = grupe
        var drugi: String = dan
        var treci: String = search

        if(prvi == "Grupe"){
            prvi = ""
        }
        if(drugi == "Dani"){
            drugi = ""
        }

        val subscription = rasporedRepository
            .filter(prvi,drugi,treci)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    rasporedState.value = RasporedState.Success(it)
                },
                {
                    rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
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