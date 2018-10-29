package chemilmakhlouta.crapchatapp.application.injection.component

import chemilmakhlouta.crapchatapp.application.injection.annotation.PerScreen
import chemilmakhlouta.crapchatapp.application.injection.module.ActivityModule
import chemilmakhlouta.crapchatapp.presentation.view.JobSearchActivity
import chemilmakhlouta.crapchatapp.presentation.view.JobsListActivity
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