package chemilmakhlouta.chemchatapp.application.injection.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */
@Module
class RetrofitModule {
    companion object {
        const val RETROFIT = "RETROFIT"
    }

    @Provides
    @Singleton
    @Named(RETROFIT)
    fun provideRetrofit(httpClient: OkHttpClient,
                        retrofitBuilder: Retrofit.Builder): Retrofit =
            retrofitBuilder.client(httpClient)
                    .baseUrl("https://jobsearch-api-mobile.cloud.seek.com.au")
                    .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(okHttpBuilder: OkHttpClient.Builder): OkHttpClient =
            okHttpBuilder.addNetworkInterceptor({ it.proceed(it.request()) })
                    .build()

    @Provides
    @Singleton
    fun provideOkHttpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
}