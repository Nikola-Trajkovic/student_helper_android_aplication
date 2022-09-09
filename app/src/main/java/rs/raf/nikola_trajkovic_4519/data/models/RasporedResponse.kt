package rs.raf.nikola_trajkovic_4519.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RasporedResponse(
    val id: Long?,
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String,
)