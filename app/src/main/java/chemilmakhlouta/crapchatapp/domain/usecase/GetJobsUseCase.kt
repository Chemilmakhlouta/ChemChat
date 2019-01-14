package chemilmakhlouta.crapchatapp.domain.usecase

import chemilmakhlouta.crapchatapp.application.injection.annotation.Mockable
import chemilmakhlouta.crapchatapp.domain.JobsRepository
import chemilmakhlouta.crapchatapp.domain.model.ChatObject
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
@Mockable
class GetJobsUseCase @Inject constructor(private val jobsRepository: JobsRepository) {

    fun getJobs(keywords: String, location: String): Single<List<ChatObject>> =
            jobsRepository.getJobs(keywords, location)
}