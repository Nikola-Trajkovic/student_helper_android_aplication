package rs.raf.nikola_trajkovic_4519.presentation.view.states

sealed class AddBeleskeState {
    object Success: AddBeleskeState()
    data class Error(val message: String): AddBeleskeState()
}