package rs.raf.nikola_trajkovic_4519.presentation.contract

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import rs.raf.nikola_trajkovic_4519.data.models.Beleske
import rs.raf.nikola_trajkovic_4519.presentation.view.states.AddBeleskeState
import rs.raf.nikola_trajkovic_4519.presentation.view.states.BeleskeState
import rs.raf.nikola_trajkovic_4519.presentation.view.states.RasporedState

interface MainContract {

    interface ViewModel {

        val rasporedState: LiveData<RasporedState>

        fun fetchAllRaspored()
        fun getAllRaspored()
        fun filter(grupe: String, dan: String, search: String)
    }

    interface ViewModelBeleske {

        val beleskeState: LiveData<BeleskeState>
        val addDone: LiveData<AddBeleskeState>

        fun addBeleske(naslov: String, content:String)
        fun getAllBeleske()
        fun getBeleskeFilter(name: String)
        fun update(id:Long, naslov: String, content: String)
        fun archive(id:Long)
        fun delete(id:Long)
        fun getArchived()


    }

}