package chemilmakhlouta.seekapp.data

import chemilmakhlouta.seekapp.data.model.JobsListResponse
import chemilmakhlouta.seekapp.domain.JobsRepository
import chemilmakhlouta.seekapp.domain.model.JobObject
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

class JobsService @Inject constructor(retrofit: Retrofit) : JobsRepository {

    private val client = retrofit.create(JobsClient::class.java)

    override fun getJobs(): Single<List<JobObject>> =
            client.getJobs().map { mapToDomainJobsList(it.data) }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


    private interface JobsClient {
        @GET("/search")
        fun getJobs(): Single<JobsListResponse>
    }

}