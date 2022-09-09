package rs.raf.nikola_trajkovic_4519.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.nikola_trajkovic_4519.data.models.BeleskeEntity
import rs.raf.nikola_trajkovic_4519.data.models.RasporedEntity

@Database(
    entities = [RasporedEntity::class, BeleskeEntity::class],
    version = 2,
    exportSchema = false)
@TypeConverters()
abstract class RasporedDataBase : RoomDatabase() {
    abstract fun getRasporedDao(): RasporedDao
    abstract fun getBeleskeDao(): BeleskeDao
}