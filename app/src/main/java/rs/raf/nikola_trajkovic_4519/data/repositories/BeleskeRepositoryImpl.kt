package rs.raf.nikola_trajkovic_4519.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.nikola_trajkovic_4519.data.datasources.local.BeleskeDao
import rs.raf.nikola_trajkovic_4519.data.models.Beleske
import rs.raf.nikola_trajkovic_4519.data.models.BeleskeEntity

class BeleskeRepositoryImpl(
    private val localDataSource: BeleskeDao
): BeleskeRepository {


    override fun insert(naslov: String, content: String): Completable {

        val beleskeEntity = BeleskeEntity(null,naslov,content,false)
        return localDataSource
            .insert(beleskeEntity)
    }

    override fun delete(id: Long): Completable {
        return localDataSource
            .delete(id)
    }


    override fun getAll(): Observable<List<Beleske>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Beleske(it.id, it.naslov, it.content, it.arhivirano)
                }
            }
    }


    override fun update(id: Long, naslov: String, content: String): Completable {
        return localDataSource
            .update(id,naslov,content)
    }


    override fun getBeleskeFilter(naslov: String): Observable<List<Beleske>> {
        return localDataSource
            .getBeleskeFilter(naslov)
            .map {
                it.map {
                    Beleske(it.id, it.naslov, it.content, it.arhivirano)
                }
            }
    }

    override fun archive(id: Long): Completable {
        return localDataSource
            .archive(id)
    }

    override fun getArchived(): Observable<List<Beleske>> {
        return localDataSource
            .getArchived()
            .map {
                it.map {
                    Beleske(it.id, it.naslov, it.content, it.arhivirano)
                }
            }
    }
}