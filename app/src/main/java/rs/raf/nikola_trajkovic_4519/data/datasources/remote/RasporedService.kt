package rs.raf.nikola_trajkovic_4519.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.nikola_trajkovic_4519.data.models.RasporedResponse

interface RasporedService {

    @GET("raspored/json.php")
    fun getAll(@Query("limit") limit: Int = 1000): Observable<List<RasporedResponse>>

}