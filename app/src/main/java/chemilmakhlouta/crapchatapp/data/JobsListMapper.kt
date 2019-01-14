package chemilmakhlouta.crapchatapp.data

import chemilmakhlouta.crapchatapp.data.model.JobObjectResponse
import chemilmakhlouta.crapchatapp.domain.model.ChatObject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
fun mapToDomainJobsList(jobsList: List<JobObjectResponse>?): List<ChatObject> {
    val mappedJobsList: MutableList<ChatObject> = mutableListOf()

    jobsList.let {
        it?.map {
            ChatObject(it.title, it.id, it.teaser)
        }?.let {
            mappedJobsList.addAll(it)
        }
    }

    return mappedJobsList
}