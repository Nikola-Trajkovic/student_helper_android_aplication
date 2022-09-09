package rs.raf.nikola_trajkovic_4519.presentation.view.states

import rs.raf.nikola_trajkovic_4519.data.models.Beleske

sealed class BeleskeState {
    object Delete: BeleskeState()
    object Update: BeleskeState()
    object Loading: BeleskeState()
    object DataFetched: BeleskeState()
    data class Success(val beleske: List<Beleske>): BeleskeState()
    data class Error(val message: String): BeleskeState()
}