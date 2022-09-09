package rs.raf.nikola_trajkovic_4519.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beleske")
data class BeleskeEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val naslov: String,
    val content: String,
    val arhivirano: Boolean,

)
