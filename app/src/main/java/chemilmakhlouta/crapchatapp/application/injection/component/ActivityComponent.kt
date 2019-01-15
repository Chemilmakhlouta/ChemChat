package chemilmakhlouta.crapchatapp.application.injection.component

import chemilmakhlouta.crapchatapp.application.injection.annotation.PerScreen
import chemilmakhlouta.crapchatapp.application.injection.module.ActivityModule
import chemilmakhlouta.crapchatapp.presentation.chats.view.ChatsListActivity
import chemilmakhlouta.crapchatapp.presentation.chats.view.LatestChatsActivity
import chemilmakhlouta.crapchatapp.presentation.login.view.LoginActivity
import chemilmakhlouta.crapchatapp.presentation.registration.view.RegisterActivity
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
    fun inject(activity: LoginActivity)
    fun inject(activity: RegisterActivity)
}