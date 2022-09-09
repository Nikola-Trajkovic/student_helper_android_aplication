package rs.raf.nikola_trajkovic_4519.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.nikola_trajkovic_4519.data.datasources.local.RasporedDao
import rs.raf.nikola_trajkovic_4519.data.datasources.remote.RasporedService
import rs.raf.nikola_trajkovic_4519.data.models.Raspored
import rs.raf.nikola_trajkovic_4519.data.models.RasporedEntity
import rs.raf.nikola_trajkovic_4519.data.models.Resource
import timber.log.Timber

class RasporedRepositoryImpl(
    private val localDataSource: RasporedDao,
    private val remoteDataSource: RasporedService
) : RasporedRepository {

    override fun fetchAll(): Observable<Resource<Unit>> {

        return remoteDataSource
            .getAll()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.map {
                    RasporedEntity(
                        it.id,
                        it.predmet,
                        it.tip,
                        it.nastavnik,
                        it.grupe,
                        it.dan,
                        it.termin,
                        it.ucionica,
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
        // Kada zelimo sami da kontrolisemo sta se desava sa greskom, umesto da je samo prepustimo
        // error handleru, mozemo iskoristiti operator onErrorReturn, tada sami na osnovu greske
        // odlucujemo koju vrednost cemo da vratimo. Ta vrednost mora biti u skladu sa povratnom
        // vrednoscu lanca.
        // Kada se iskoristi onErrorReturn onError lambda u viewmodelu nece biti izvrsena,
        // nego ce umesdto nje biti izvsena onNext koja ce kao parametar primiti vrednost koja
        // je vracena iz onErrorReturn
        // Obicno se koristi kada je potrebno proveriti koji kod greske je vratio server.
//            .onErrorReturn {
//                when(it) {
//                    is HttpException -> {
//                        when(it.code()) {
//                            in 400..499 -> {
//
//                            }
//                        }
//                    }
//                }
//                Timber.e("ON ERROR RETURN")
//            }
    }

    override fun getAll(): Observable<List<Raspored>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Raspored(it.id, it.predmet, it.tip, it.nastavnik, it.grupe, it.dan, it.termin, it.ucionica)
                }
            }
    }

    override fun filter(grupe: String, dan: String, search: String): Observable<List<Raspored>> {
        return localDataSource
            .filter(grupe,dan,search)
            .map {
                it.map {
                    Raspored(it.id, it.predmet, it.tip, it.nastavnik, it.grupe, it.dan, it.termin, it.ucionica)
                }
            }
    }

    override fun insert(raspored: Raspored): Completable {
        val rasporedEntity = RasporedEntity(raspored.id, raspored.predmet, raspored.tip, raspored.nastavnik, raspored.grupe, raspored.dan, raspored.termin, raspored.ucionica)
        return localDataSource
            .insert(rasporedEntity)
    }

}