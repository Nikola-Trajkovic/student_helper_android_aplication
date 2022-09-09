package rs.raf.nikola_trajkovic_4519.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.nikola_trajkovic_4519.data.models.RasporedEntity

@Dao
abstract class RasporedDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: RasporedEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<RasporedEntity>): Completable

    @Query("SELECT * FROM raspored")
    abstract fun getAll(): Observable<List<RasporedEntity>>

    @Query("DELETE FROM raspored")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<RasporedEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM raspored where grupe like '%' || :grupe || '%' and dan like '%' || :dan || '%' and (nastavnik like '%' || :search || '%' or predmet like '%' || :search || '%') ")
    abstract fun filter(grupe: String, dan: String, search: String): Observable<List<RasporedEntity>>

}