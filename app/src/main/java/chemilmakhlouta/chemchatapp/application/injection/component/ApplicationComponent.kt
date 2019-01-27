package chemilmakhlouta.chemchatapp.application.injection.component

import android.app.Application
import chemilmakhlouta.chemchatapp.application.injection.module.*
import chemilmakhlouta.chemchatapp.data.chats.UserRepository
import chemilmakhlouta.chemchatapp.data.login.LoginRepository
import chemilmakhlouta.chemchatapp.data.registration.RegistrationRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ActivityModule::class, RetrofitModule::class, UserModule::class))
interface ApplicationComponent {

    fun inject(application: Application)

    fun getRegistrationRepository(): RegistrationRepository
    fun getLoginRepository(): LoginRepository
    fun getUserRepository(): UserRepository
}