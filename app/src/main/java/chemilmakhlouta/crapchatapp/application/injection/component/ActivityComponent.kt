package chemilmakhlouta.crapchatapp.application.injection.component

import chemilmakhlouta.crapchatapp.application.injection.annotation.PerScreen
import chemilmakhlouta.crapchatapp.application.injection.module.ActivityModule
import chemilmakhlouta.crapchatapp.presentation.view.ChatsListActivity
import chemilmakhlouta.crapchatapp.presentation.view.LatestChatsActivity
import dagger.Component

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

@PerScreen
@Component(dependencies = arrayOf(ApplicationComponent::class),
           modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activity: ChatsListActivity)
    fun inject(activity: LatestChatsActivity)
}