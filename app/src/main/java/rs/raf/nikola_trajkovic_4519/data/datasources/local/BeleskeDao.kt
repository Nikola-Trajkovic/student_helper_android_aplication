package rs.raf.nikola_trajkovic_4519.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.nikola_trajkovic_4519.data.models.BeleskeEntity


@Dao
abstract class BeleskeDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: BeleskeEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<BeleskeEntity>): Completable

    @Query("SELECT * FROM beleske")
    abstract fun getAll(): Observable<List<BeleskeEntity>>

    @Query("DELETE FROM beleske")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<BeleskeEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM beleske WHERE naslov LIKE :naslov || '%'")
    abstract fun getBeleskeFilter(naslov: String): Observable<List<BeleskeEntity>>

    @Query("UPDATE beleske SET naslov= :naslov, content= :content WHERE id = :id")
    abstract fun update(id:Long, naslov: String, content: String): Completable

    @Query("UPDATE beleske SET arhivirano=1 WHERE id = :id")
    abstract fun archive(id:Long): Completable

    @Query("DELETE FROM beleske WHERE id= :id")
    abstract fun delete(id:Long): Completable

    @Query("SELECT * FROM beleske WHERE arhivirano = 1")
    abstract fun getArchived(): Observable<List<BeleskeEntity>>




}