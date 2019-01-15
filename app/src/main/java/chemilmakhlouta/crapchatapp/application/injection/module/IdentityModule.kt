package chemilmakhlouta.crapchatapp.application.injection.module

import chemilmakhlouta.crapchatapp.data.login.LoginRepository
import chemilmakhlouta.crapchatapp.data.login.LoginService
import chemilmakhlouta.crapchatapp.data.registration.RegistrationRepository
import chemilmakhlouta.crapchatapp.data.registration.RegistrationService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

@Module
class IdentityModule {

    @Provides
    @Singleton
    fun providesLoginRepository(): LoginRepository = LoginService()

    @Provides
    @Singleton
    fun providesRegistrationRepository(): RegistrationRepository = RegistrationService()
}