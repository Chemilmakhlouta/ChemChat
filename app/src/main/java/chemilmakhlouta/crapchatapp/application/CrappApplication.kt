package chemilmakhlouta.crapchatapp.application

/**
 * Created by Chemil Makhlouta on 24/7/18.
 */

import android.support.multidex.MultiDexApplication
import chemilmakhlouta.crapchatapp.application.injection.component.ApplicationComponent
import chemilmakhlouta.crapchatapp.application.injection.component.DaggerApplicationComponent
import chemilmakhlouta.crapchatapp.application.injection.module.ApplicationModule
import com.facebook.drawee.backends.pipeline.Fresco


/**
 * Created by Chemil Makhlouta on 25/6/18.
 */
class CrappApplication : MultiDexApplication() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        applicationComponent.inject(this)

        Fresco.initialize(this);
    }
}