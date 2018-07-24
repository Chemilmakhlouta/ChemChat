package chemilmakhlouta.seekapp.application.injection.component

import android.app.Application
import chemilmakhlouta.seekapp.application.injection.module.ActivityModule
import chemilmakhlouta.seekapp.application.injection.module.ApplicationModule
import chemilmakhlouta.seekapp.application.injection.module.JobsModule
import chemilmakhlouta.seekapp.application.injection.module.RetrofitModule
import chemilmakhlouta.seekapp.domain.JobsRepository
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