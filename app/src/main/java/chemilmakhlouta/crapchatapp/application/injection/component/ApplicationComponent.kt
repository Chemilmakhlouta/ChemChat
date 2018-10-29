package chemilmakhlouta.crapchatapp.application.injection.component

import android.app.Application
import chemilmakhlouta.crapchatapp.application.injection.module.*
import chemilmakhlouta.crapchatapp.domain.JobsRepository
import chemilmakhlouta.crapchatapp.domain.ProfileRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ActivityModule::class, RetrofitModule::class, JobsModule::class,
        ProfileModule::class))
interface ApplicationComponent {

    fun inject(application: Application)

    fun getJobsRepository(): JobsRepository
    fun getProfileRepository(): ProfileRepository
}