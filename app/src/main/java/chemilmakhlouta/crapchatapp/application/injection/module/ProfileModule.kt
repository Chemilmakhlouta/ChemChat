package chemilmakhlouta.crapchatapp.application.injection.module

import chemilmakhlouta.crapchatapp.data.ProfileService
import chemilmakhlouta.crapchatapp.domain.ProfileRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton
import com.google.firebase.storage.FirebaseStorage


/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

@Module
class ProfileModule {

    @Provides
    @Singleton
    fun provideProfileRepository(@Named(RetrofitModule.RETROFIT) retrofit: Retrofit): ProfileRepository {
        val firebaseInstance = FirebaseStorage
                .getInstance()
        return ProfileService(firebaseInstance)
    }
}