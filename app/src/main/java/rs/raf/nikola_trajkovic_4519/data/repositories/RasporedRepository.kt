package rs.raf.nikola_trajkovic_4519.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.nikola_trajkovic_4519.data.models.Raspored
import rs.raf.nikola_trajkovic_4519.data.models.Resource

interface RasporedRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Raspored>>
    fun filter(grupe: String, dan: String, search: String): Observable<List<Raspored>>
    fun insert(raspored: Raspored): Completable

}