package rs.raf.nikola_trajkovic_4519.presentation.view.states

import rs.raf.nikola_trajkovic_4519.data.models.Raspored

sealed class RasporedState {
    object Loading: RasporedState()
    object DataFetched: RasporedState()
    data class Success(val rasporeds: List<Raspored>): RasporedState()
    data class Error(val message: String): RasporedState()
}