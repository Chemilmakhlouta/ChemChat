package chemilmakhlouta.crapchatapp.application.injection.component

import android.app.Application
import chemilmakhlouta.crapchatapp.application.injection.module.*
import chemilmakhlouta.crapchatapp.data.login.LoginRepository
import chemilmakhlouta.crapchatapp.data.registration.RegistrationRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ActivityModule::class, RetrofitModule::class, IdentityModule::class))
interface ApplicationComponent {

    fun inject(application: Application)

    fun getRegistrationRepository(): RegistrationRepository
    fun getLoginRepository(): LoginRepository
}