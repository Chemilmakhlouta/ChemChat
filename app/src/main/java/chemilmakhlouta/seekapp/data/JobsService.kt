package chemilmakhlouta.seekapp.data

import chemilmakhlouta.seekapp.data.model.JobsListResponse
import chemilmakhlouta.seekapp.domain.JobsRepository
import chemilmakhlouta.seekapp.domain.model.JobObject
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class JobsService @Inject constructor(retrofit: Retrofit) : JobsRepository {

    private companion object {
        const val PARAM_KEYWORDS = "keywords"
        const val PARAM_LOCATION = "where"
    }

    private val client = retrofit.create(JobsClient::class.java)

    override fun getJobs(keywords: String, location: String): Single<List<JobObject>> =
            client.getJobs(keywords, location).map { mapToDomainJobsList(it.data) }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


    private interface JobsClient {
        @GET("/search")
        fun getJobs(@Query(PARAM_KEYWORDS) keywords: String,
                    @Query(PARAM_LOCATION) location: String): Single<JobsListResponse>
    }

}