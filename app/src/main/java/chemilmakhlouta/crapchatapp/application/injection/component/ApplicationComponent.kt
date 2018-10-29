package chemilmakhlouta.crapchatapp.application.injection.component

import android.app.Application
import chemilmakhlouta.crapchatapp.application.injection.module.ActivityModule
import chemilmakhlouta.crapchatapp.application.injection.module.ApplicationModule
import chemilmakhlouta.crapchatapp.application.injection.module.JobsModule
import chemilmakhlouta.crapchatapp.application.injection.module.RetrofitModule
import chemilmakhlouta.crapchatapp.domain.JobsRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ActivityModule::class, RetrofitModule::class, JobsModule::class))
interface ApplicationComponent {

    fun inject(application: Application)

    fun getJobsRepository(): JobsRepository
}