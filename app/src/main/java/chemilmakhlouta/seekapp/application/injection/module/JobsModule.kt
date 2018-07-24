package chemilmakhlouta.seekapp.application.injection.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

@Module
class JobsModule {

    @Provides
    @Singleton
    fun provideNewsRepository(@Named(RetrofitModule.RETROFIT) retrofit: Retrofit): JobsRepository = JobsService(retrofit)
}