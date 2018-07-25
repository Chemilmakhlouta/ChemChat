package chemilmakhlouta.seekapp.domain.usecase

import chemilmakhlouta.seekapp.application.injection.annotation.Mockable
import chemilmakhlouta.seekapp.domain.JobsRepository
import chemilmakhlouta.seekapp.domain.model.JobObject
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
@Mockable
class GetJobsUseCase @Inject constructor(private val jobsRepository: JobsRepository) {

    fun getJobs(keywords: String, location: String): Single<List<JobObject>> =
            jobsRepository.getJobs(keywords, location)
}