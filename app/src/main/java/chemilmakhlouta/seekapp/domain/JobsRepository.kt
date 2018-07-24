package chemilmakhlouta.seekapp.domain

import chemilmakhlouta.seekapp.domain.model.JobObject
import io.reactivex.Single

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
interface JobsRepository {
    fun getJobs(): Single<List<JobObject>>
}