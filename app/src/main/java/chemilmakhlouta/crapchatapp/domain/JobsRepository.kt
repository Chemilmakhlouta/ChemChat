package chemilmakhlouta.crapchatapp.domain

import chemilmakhlouta.crapchatapp.domain.model.ChatObject
import io.reactivex.Single

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
interface JobsRepository {
    fun getJobs(keywords: String, location: String): Single<List<ChatObject>>
}