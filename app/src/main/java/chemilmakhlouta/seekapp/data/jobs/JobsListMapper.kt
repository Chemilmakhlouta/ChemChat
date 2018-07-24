package chemilmakhlouta.seekapp.data.jobs

import chemilmakhlouta.seekapp.data.jobs.model.JobObjectResponse
import chemilmakhlouta.seekapp.domain.model.JobObject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
fun mapToDomainJobsList(newsList: List<JobObjectResponse>?): List<JobObject> {
    val mappedNewsList: MutableList<JobObject> = mutableListOf()

    newsList.let {
        it?.map {
            JobObject(it.title)
        }?.let {
            mappedNewsList.addAll(it)
        }
    }

    return mappedNewsList
}