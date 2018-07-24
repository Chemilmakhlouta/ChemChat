package chemilmakhlouta.seekapp.application.injection.component

import chemilmakhlouta.seekapp.application.injection.annotation.PerScreen
import chemilmakhlouta.seekapp.application.injection.module.ActivityModule
import chemilmakhlouta.seekapp.presentation.view.JobSearchActivity
import chemilmakhlouta.seekapp.presentation.view.JobsListActivity
import dagger.Component

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

@PerScreen
@Component(dependencies = arrayOf(ApplicationComponent::class),
           modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activity: JobSearchActivity)
    fun inject(activity: JobsListActivity)
}