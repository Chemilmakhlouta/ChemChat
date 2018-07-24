package chemilmakhlouta.seekapp.data.jobs

import chemilmakhlouta.seekapp.data.jobs.model.JobObjectResponse
import chemilmakhlouta.seekapp.domain.model.JobObject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
fun mapToDomainJobsList(jobsList: List<JobObjectResponse>?): List<JobObject> {
    val mappedJobsList: MutableList<JobObject> = mutableListOf()

    jobsList.let {
        it?.map {
            JobObject(it.title, it.id, it.teaser)
        }?.let {
            mappedJobsList.addAll(it)
        }
    }

    return mappedJobsList
}