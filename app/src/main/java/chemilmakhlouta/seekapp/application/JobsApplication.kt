package chemilmakhlouta.seekapp.application

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

import android.support.multidex.MultiDexApplication
import chemilmakhlouta.seekapp.application.injection.component.ApplicationComponent
import chemilmakhlouta.seekapp.application.injection.component.DaggerApplicationComponent
import chemilmakhlouta.seekapp.application.injection.module.ApplicationModule


/**
 * Created by Chemil Makhlouta on 25/6/18.
 */
class JobsApplication : MultiDexApplication() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        applicationComponent.inject(this)
    }
}