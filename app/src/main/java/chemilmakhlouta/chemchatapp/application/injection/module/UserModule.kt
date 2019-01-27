package chemilmakhlouta.chemchatapp.application.injection.module

import chemilmakhlouta.chemchatapp.data.chats.UserDataStore
import chemilmakhlouta.chemchatapp.data.chats.UserRepository
import chemilmakhlouta.chemchatapp.data.chats.UserService
import chemilmakhlouta.chemchatapp.data.login.LoginRepository
import chemilmakhlouta.chemchatapp.data.login.LoginService
import chemilmakhlouta.chemchatapp.data.registration.RegistrationRepository
import chemilmakhlouta.chemchatapp.data.registration.RegistrationService
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