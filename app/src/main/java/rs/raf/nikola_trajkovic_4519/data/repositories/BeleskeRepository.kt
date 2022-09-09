package rs.raf.nikola_trajkovic_4519.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.nikola_trajkovic_4519.data.models.Beleske
import rs.raf.nikola_trajkovic_4519.data.models.BeleskeEntity

interface BeleskeRepository {

    fun insert(naslov: String, content: String): Completable
    fun delete(id:Long): Completable
    fun getAll(): Observable<List<Beleske>>
    fun getBeleskeFilter(naslov: String): Observable<List<Beleske>>
    fun update(id:Long, naslov: String, content: String): Completable
    fun archive(id:Long): Completable
    fun getArchived(): Observable<List<Beleske>>


}