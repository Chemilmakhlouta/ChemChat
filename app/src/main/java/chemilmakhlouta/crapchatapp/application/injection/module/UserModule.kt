package chemilmakhlouta.crapchatapp.application.injection.module

import chemilmakhlouta.crapchatapp.data.chats.UserDataStore
import chemilmakhlouta.crapchatapp.data.chats.UserRepository
import chemilmakhlouta.crapchatapp.data.chats.UserService
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
class UserModule {

    @Provides
    @Singleton
    fun providesLoginRepository(): LoginRepository = LoginService()

    @Provides
    @Singleton
    fun providesRegistrationRepository(): RegistrationRepository = RegistrationService()

    @Provides
    @Singleton
    fun providesUserRepository(userDataStore: UserDataStore): UserRepository = UserService(userDataStore)


    @Provides
    @Singleton
    fun providesUserDataStore(): UserDataStore = UserDataStore()
}