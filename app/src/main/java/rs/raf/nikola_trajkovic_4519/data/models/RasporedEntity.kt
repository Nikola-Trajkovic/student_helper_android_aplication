package rs.raf.nikola_trajkovic_4519.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "raspored")
data class RasporedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String,
)