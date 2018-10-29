package chemilmakhlouta.crapchatapp.domain

import chemilmakhlouta.crapchatapp.domain.model.JobObject
import io.reactivex.Single

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
interface JobsRepository {
    fun getJobs(keywords: String, location: String): Single<List<JobObject>>
}