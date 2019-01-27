package chemilmakhlouta.chemchatapp.application.injection.component

import chemilmakhlouta.chemchatapp.application.injection.annotation.PerScreen
import chemilmakhlouta.chemchatapp.application.injection.module.ActivityModule
import chemilmakhlouta.chemchatapp.presentation.chats.view.ChatsListActivity
import chemilmakhlouta.chemchatapp.presentation.chats.view.LatestChatsActivity
import chemilmakhlouta.chemchatapp.presentation.chats.view.SelectRecipientActivity
import chemilmakhlouta.chemchatapp.presentation.login.view.LoginActivity
import chemilmakhlouta.chemchatapp.presentation.registration.view.RegisterActivity
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
    fun inject(activity: SelectRecipientActivity)
}